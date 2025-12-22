package com.loopwish.design

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import org.json.JSONObject

enum class LoopwishTheme {
    Light,
    Dark,
}

enum class LoopwishColorRole(val key: String) {
    TextPrimary("color.text.primary"),
    TextSecondary("color.text.secondary"),
    TextOnAccent("color.text.onAccent"),

    SurfaceCanvas("color.surface.canvas"),
    SurfaceElevated("color.surface.elevated"),
    SurfaceAccent("color.surface.accent"),

    BorderDefault("color.border.default"),
    BorderFocus("color.border.focus"),

    ActionPrimaryBackground("color.action.primary.bg"),
    ActionPrimaryForeground("color.action.primary.fg"),
    ActionSecondaryForeground("color.action.secondary.fg"),

    StateHover("color.state.hover"),
    StatePressed("color.state.pressed"),
    StateDisabled("color.state.disabled"),
}

@Composable
fun loopwishTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    val theme = if (darkTheme) LoopwishTheme.Dark else LoopwishTheme.Light

    val context = LocalContext.current
    val design = LoopwishDesign.fromAssets(context)

    val scheme =
        if (darkTheme) {
            darkColorScheme(
                primary = design.color(LoopwishColorRole.ActionPrimaryBackground, theme),
                secondary = design.color(LoopwishColorRole.ActionSecondaryForeground, theme),
                background = design.color(LoopwishColorRole.SurfaceCanvas, theme),
                surface = design.color(LoopwishColorRole.SurfaceElevated, theme),
                onPrimary = design.color(LoopwishColorRole.ActionPrimaryForeground, theme),
                onSurface = design.color(LoopwishColorRole.TextPrimary, theme),
                outline = design.color(LoopwishColorRole.BorderDefault, theme),
            )
        } else {
            lightColorScheme(
                primary = design.color(LoopwishColorRole.ActionPrimaryBackground, theme),
                secondary = design.color(LoopwishColorRole.ActionSecondaryForeground, theme),
                background = design.color(LoopwishColorRole.SurfaceCanvas, theme),
                surface = design.color(LoopwishColorRole.SurfaceElevated, theme),
                onPrimary = design.color(LoopwishColorRole.ActionPrimaryForeground, theme),
                onSurface = design.color(LoopwishColorRole.TextPrimary, theme),
                outline = design.color(LoopwishColorRole.BorderDefault, theme),
            )
        }

    MaterialTheme(
        colorScheme = scheme,
        content = content,
    )
}

class LoopwishDesign private constructor(
    private val primitivesColors: Map<String, String>,
    private val semanticLight: Map<String, String>,
    private val semanticDark: Map<String, String>,
    val tagline: String,
) {
    fun hex(
        role: LoopwishColorRole,
        theme: LoopwishTheme,
    ): String {
        val raw =
            when (theme) {
                LoopwishTheme.Light -> semanticLight[role.key]
                LoopwishTheme.Dark -> semanticDark[role.key]
            }

        val resolved = raw?.let { resolveTokenValue(it) }
        return resolved ?: defaultHex(role)
    }

    fun color(
        role: LoopwishColorRole,
        theme: LoopwishTheme,
    ): Color {
        return Color(parseHexToArgb(hex(role, theme)))
    }

    private fun resolveTokenValue(raw: String): String? {
        if (raw.startsWith("#")) {
            return raw
        }

        // Supports aliases like: {primitives.colors.textDark}
        if (raw.startsWith("{") && raw.endsWith("}")) {
            val path = raw.substring(1, raw.length - 1)
            val prefix = "primitives.colors."
            if (!path.startsWith(prefix)) return null
            val key = path.removePrefix(prefix)
            return primitivesColors[key]
        }

        return null
    }

    companion object {
        private var cached: LoopwishDesign? = null

        fun fromAssets(context: Context): LoopwishDesign {
            cached?.let { return it }

            val json = readAssetText(context, "design-tokens/tokens.json")
            val root = JSONObject(json)

            val content = root.getJSONObject("content")
            val tagline = content.getString("tagline")

            val primitives = root.getJSONObject("primitives").getJSONObject("colors")
            val primitivesColors = primitives.toStringMap()

            val semantic = root.getJSONObject("semantic")
            val semanticLight = semantic.getJSONObject("light").toStringMap()
            val semanticDark = semantic.getJSONObject("dark").toStringMap()

            return LoopwishDesign(
                primitivesColors = primitivesColors,
                semanticLight = semanticLight,
                semanticDark = semanticDark,
                tagline = tagline,
            ).also { cached = it }
        }

        private fun readAssetText(
            context: Context,
            path: String,
        ): String {
            context.assets.open(path).use { input ->
                return input.bufferedReader().readText()
            }
        }

        private fun JSONObject.toStringMap(): Map<String, String> {
            val map = mutableMapOf<String, String>()
            val keys = keys()
            while (keys.hasNext()) {
                val key = keys.next()
                map[key] = getString(key)
            }
            return map
        }

        private fun parseHexToArgb(hex: String): Int {
            val cleaned = hex.removePrefix("#")
            val rgb = cleaned.toLongOrNull(16) ?: 0
            return (0xFF shl 24) or (rgb.toInt() and 0x00FFFFFF)
        }

        private fun defaultHex(role: LoopwishColorRole): String {
            return when (role) {
                LoopwishColorRole.TextPrimary -> "#2C3E50"
                LoopwishColorRole.TextSecondary -> "#7F8C8D"
                LoopwishColorRole.TextOnAccent -> "#FFFFFF"

                LoopwishColorRole.SurfaceCanvas,
                LoopwishColorRole.SurfaceElevated,
                -> "#FFFFFF"

                LoopwishColorRole.SurfaceAccent -> "#5DADE2"

                LoopwishColorRole.BorderDefault -> "#7F8C8D"
                LoopwishColorRole.BorderFocus -> "#AF7AC5"

                LoopwishColorRole.ActionPrimaryBackground -> "#5DADE2"
                LoopwishColorRole.ActionPrimaryForeground -> "#FFFFFF"
                LoopwishColorRole.ActionSecondaryForeground -> "#AF7AC5"

                LoopwishColorRole.StateHover -> "#AF7AC5"
                LoopwishColorRole.StatePressed -> "#5DADE2"
                LoopwishColorRole.StateDisabled -> "#7F8C8D"
            }
        }
    }
}
