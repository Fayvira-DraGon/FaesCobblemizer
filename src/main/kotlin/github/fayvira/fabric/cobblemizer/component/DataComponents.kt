package github.fayvira.fabric.cobblemizer.component

import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.pokemon.Species.Companion.BY_IDENTIFIER_CODEC
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.MOD_ID
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.MOD_NAME
import github.fayvira.fabric.cobblemizer.Cobblemizer.Companion.LOGGER
import net.minecraft.component.ComponentType
import net.minecraft.component.ComponentType.builder
import net.minecraft.registry.Registries.DATA_COMPONENT_TYPE
import net.minecraft.registry.Registry.register
import net.minecraft.util.Identifier

object DataComponents {
  val SPECIES_COMPONENT: ComponentType<Species> = register(
    DATA_COMPONENT_TYPE,
    Identifier.of(MOD_ID,"species_component"),
    builder<Species>().codec(BY_IDENTIFIER_CODEC).build()
  )

  fun init() {
    LOGGER.info("Register Data Components for $MOD_NAME")
  }
}
