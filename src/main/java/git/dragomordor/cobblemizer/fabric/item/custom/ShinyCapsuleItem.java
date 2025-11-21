package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.api.pokemon.evolution.PreEvolution;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import git.dragomordor.cobblemizer.fabric.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public class ShinyCapsuleItem extends PokemonUseItem {
  public ShinyCapsuleItem() {
    super(new Settings().maxCount(1).component(ModComponents.SPECIES_COMPONENT, ""));
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    boolean isShiny = pokemon.getShiny(); // Determines if Pokémon is shiny
    String pokemonName = pokemon.getSpecies().getName(); // Determines if Pokémon is shiny
    String species = getSpecies(itemStack);
    String evoLine = getEvoLine(pokemon);

    if (species.isEmpty()){
      if (!isShiny) {
        player.sendMessage(Text.of("The Pokémon must be Shiny to Capture its Shiny Status"));
        return ActionResult.FAIL;
      }
      itemStack.set(ModComponents.SPECIES_COMPONENT, evoLine);
      pokemon.setShiny(false);
      player.sendMessage(Text.of("Successfully Captured the Shiny Status of this " + pokemonName));
      return ActionResult.SUCCESS;
    } else if (!species.equals(evoLine) && !isShiny) {
      player.sendMessage(Text.of("This " + pokemonName + " is not in the " + species + " Evolutionary Line"));
      return ActionResult.FAIL;
    } else {
      if (isShiny) {
        player.sendMessage(Text.of("This " + pokemonName + " is already Shiny"));
        return ActionResult.FAIL;
      }
      pokemon.setShiny(true);
      player.sendMessage(Text.of("Successfully applied the Captured Shiny Status to this " + pokemonName));
      itemStack.decrement(1); // remove item after use
      return ActionResult.SUCCESS;
    }
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    String species = getSpecies(itemStack);
    list.add(species.isEmpty() ?
      Text.of("Capture the Shiny Status of a Pokémon") :
      Text.of("Apply Captured Shiny Status to a Pokémon in the " + species + "Evolutionary Line"));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }

  private String getSpecies(ItemStack itemStack) {
    return itemStack.getOrDefault(ModComponents.SPECIES_COMPONENT, "");
  }

  private String getEvoLine(Pokemon pokemon) {
    Species firstInLine = pokemon.getSpecies();
    while (true) {
      PreEvolution preEvolution = firstInLine.getStandardForm().getPreEvolution();
      if (preEvolution == null) {
        break;
      }
      firstInLine = preEvolution.getSpecies();
    }
    return firstInLine.getName();
  }
}
