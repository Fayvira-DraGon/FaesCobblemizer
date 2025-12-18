package github.fayvira.fabric.cobblemizer

import com.cobblemon.mod.common.Cobblemon.config
import github.fayvira.fabric.cobblemizer.component.DataComponents
import github.fayvira.fabric.cobblemizer.component.DataComponents.SPECIES_COMPONENT
import github.fayvira.fabric.cobblemizer.item.Items
import github.fayvira.fabric.cobblemizer.item.Items.SHINY_CAPSULE
import net.fabricmc.api.ModInitializer
import net.minecraft.client.item.ModelPredicateProviderRegistry.register
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager.getLogger
import org.apache.logging.log4j.Logger

class Cobblemizer : ModInitializer {

  override fun onInitialize() {
    // Register data components
    DataComponents.init()
    // Register all items
    Items.init()
    // Populate global variables
    try {
      maxFriendship = config.maxPokemonFriendship
    } catch (e: Exception) {
      LOGGER.info("onInitialize - config.maxPokemonFriendship: ${e.message ?: (maxFriendship ?: "null")}")
    }
    try {
      maxLevel = config.maxPokemonLevel
    } catch (e: Exception) {
      LOGGER.info("onInitialize - config.maxPokemonLevel: ${e.message ?: (maxLevel ?: "null")}")
    }

    register(SHINY_CAPSULE, Identifier.of(MOD_ID, "filled")) { stack: ItemStack, _, _, _ -> if (stack.get(SPECIES_COMPONENT) != null) 1F else 0F }
  }

  companion object {
    const val MOD_ID: String = "cobblemizer"
    const val MOD_NAME: String = "Fae's Cobblemizer Redux" // mod name
    val LOGGER: Logger = getLogger() // create logger
    var maxLevel: Int? = null
    var maxFriendship: Int? = null
  }
}
