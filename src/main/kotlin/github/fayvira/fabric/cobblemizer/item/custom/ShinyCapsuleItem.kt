package github.fayvira.fabric.cobblemizer.item.custom

import com.cobblemon.mod.common.CobblemonSounds.ITEM_USE
import com.cobblemon.mod.common.CobblemonSounds.PC_CLICK
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.item.battle.BagItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import github.fayvira.fabric.cobblemizer.component.DataComponents.SPECIES_COMPONENT
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.TypedActionResult.pass
import net.minecraft.util.TypedActionResult.success
import net.minecraft.world.World

class ShinyCapsuleItem(
  settings: Settings = Settings().maxCount(1)
) : CobblemonItem(settings.component(SPECIES_COMPONENT, null)), PokemonSelectingItem {

  override val bagItem: BagItem? = null
  val success: SoundEvent = ITEM_USE
  val failure: SoundEvent = PC_CLICK

  override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
    val species: Species? = stack.get(SPECIES_COMPONENT)
    tooltip.add(
      Text.of(if (species == null) "Capture Pokémon's Shiny Status" else "Apply Captured Shiny Status to Pokémon in the ${species.name} Family")
    )
    super.appendTooltip(stack, context, tooltip, type)
  }

  override fun applyToPokemon(
    player: ServerPlayerEntity,
    stack: ItemStack,
    pokemon: Pokemon
  ): TypedActionResult<ItemStack> {
    if (!player.world.isClient) {
      val shiny: Boolean = pokemon.shiny
      val species: Species? = stack.getOrDefault(SPECIES_COMPONENT, null)
      if (species == null) {
        if (shiny) {
          pokemon.shiny = false
          stack.set(SPECIES_COMPONENT, getFirst(pokemon.species))
          player.sendMessage(Text.of("Captured Pokémon's Shiny Status!"))
          pokemon.entity?.playSound(success, 1F, 1F)
          return success(stack)
        } else {
          player.sendMessage(Text.of("Pokémon has no Shiny Status to capture"))
          pokemon.entity?.playSound(failure, 1F, 1F)
        }
      } else {
        val line: Boolean = getFirst(pokemon.species) == species
        if (line) {
          if (shiny) {
            player.sendMessage(Text.of("Pokémon is already Shiny"))
            pokemon.entity?.playSound(failure, 1F, 1F)
          } else {
            pokemon.shiny = true
            player.sendMessage(Text.of("Applied the Captured Shiny Status!"))
            pokemon.entity?.playSound(success, 1F, 1F)
            stack.decrementUnlessCreative(1, player)
            return success(stack)
          }
        } else {
          if (shiny) {
            player.sendMessage(Text.of("Pokémon is already Shiny & must be in the ${species.name} Family"))
            pokemon.entity?.playSound(failure, 1F, 1F)
          } else {
            player.sendMessage(Text.of("Pokémon must be in the ${species.name} Family"))
            pokemon.entity?.playSound(failure, 1F, 1F)
          }
        }
      }
    }
    return pass(stack)
  }

  fun getFirst(species: Species): Species {
    var first: Species = species
    while (true) {
      val next = first.standardForm.preEvolution?.species ?: first
      if (next == first) break else first = next
    }
    return first
  }

  override fun canUseOnPokemon(stack: ItemStack, pokemon: Pokemon): Boolean = pokemon.isPlayerOwned()

  override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> = if (user is ServerPlayerEntity) use(user, user.getStackInHand(hand)) else success(user.getStackInHand(hand))
}
