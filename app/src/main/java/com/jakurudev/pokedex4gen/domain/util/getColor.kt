package com.jakurudev.pokedex4gen.domain.util

import androidx.compose.ui.graphics.Color
import com.jakurudev.pokedex4gen.ui.theme.BugChipBackground
import com.jakurudev.pokedex4gen.ui.theme.DarkChipBackground
import com.jakurudev.pokedex4gen.ui.theme.DarkChipText
import com.jakurudev.pokedex4gen.ui.theme.DragonChipBackground
import com.jakurudev.pokedex4gen.ui.theme.DragonChipText
import com.jakurudev.pokedex4gen.ui.theme.ElectricChipBackground
import com.jakurudev.pokedex4gen.ui.theme.ElectricChipText
import com.jakurudev.pokedex4gen.ui.theme.FightingChipBackground
import com.jakurudev.pokedex4gen.ui.theme.FightingChipText
import com.jakurudev.pokedex4gen.ui.theme.FireChipBackground
import com.jakurudev.pokedex4gen.ui.theme.FireChipText
import com.jakurudev.pokedex4gen.ui.theme.FlyChipBackground
import com.jakurudev.pokedex4gen.ui.theme.FlyChipText
import com.jakurudev.pokedex4gen.ui.theme.GhostChipBackground
import com.jakurudev.pokedex4gen.ui.theme.GhostChipText
import com.jakurudev.pokedex4gen.ui.theme.GrassChipBackground
import com.jakurudev.pokedex4gen.ui.theme.GrassChipText
import com.jakurudev.pokedex4gen.ui.theme.GroundChipBackground
import com.jakurudev.pokedex4gen.ui.theme.GroundChipText
import com.jakurudev.pokedex4gen.ui.theme.IceChipBackground
import com.jakurudev.pokedex4gen.ui.theme.IceChipText
import com.jakurudev.pokedex4gen.ui.theme.NormalChipBackground
import com.jakurudev.pokedex4gen.ui.theme.NormalChipText
import com.jakurudev.pokedex4gen.ui.theme.PoisonChipBackground
import com.jakurudev.pokedex4gen.ui.theme.PoisonChipText
import com.jakurudev.pokedex4gen.ui.theme.PsychicChipBackground
import com.jakurudev.pokedex4gen.ui.theme.PsychicChipText
import com.jakurudev.pokedex4gen.ui.theme.RockChipBackground
import com.jakurudev.pokedex4gen.ui.theme.RockChipText
import com.jakurudev.pokedex4gen.ui.theme.SteelChipBackground
import com.jakurudev.pokedex4gen.ui.theme.SteelChipText
import com.jakurudev.pokedex4gen.ui.theme.WaterChipBackground
import com.jakurudev.pokedex4gen.ui.theme.WaterChipText

fun getChipTextColor(type: String) : Color {
    val colors = mutableMapOf<String, Color>()
    colors["flying"] = FlyChipText
    colors["fire"] = FireChipText
    colors["water"] = WaterChipText
    colors["steel"] = SteelChipText
    colors["bug"] = FireChipText
    colors["dragon"] = DragonChipText
    colors["electric"] = ElectricChipText
    colors["normal"] = NormalChipText
    colors["fighting"] = FightingChipText
    colors["poison"] = PoisonChipText
    colors["ground"] = GroundChipText
    colors["rock"] = RockChipText
    colors["ghost"] = GhostChipText
    colors["grass"] = GrassChipText
    colors["psychic"] = PsychicChipText
    colors["dark"] = DarkChipText
    colors["ice"] = IceChipText
    // fairy

    return colors[type]!!
}

fun getChipBackgroundColor(type: String) : Color {
    val colors = mutableMapOf<String, Color>()
    colors["flying"] = FlyChipBackground
    colors["fire"] = FireChipBackground
    colors["water"] = WaterChipBackground
    colors["steel"] = SteelChipBackground
    colors["bug"] = BugChipBackground
    colors["dragon"] = DragonChipBackground
    colors["electric"] = ElectricChipBackground
    colors["normal"] = NormalChipBackground
    colors["fighting"] = FightingChipBackground
    colors["poison"] = PoisonChipBackground
    colors["ground"] = GroundChipBackground
    colors["rock"] = RockChipBackground
    colors["ghost"] = GhostChipBackground
    colors["grass"] = GrassChipBackground
    colors["psychic"] = PsychicChipBackground
    colors["dark"] = DarkChipBackground
    colors["ice"] = IceChipBackground
    // fairy

    return colors[type]!!
}