package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public class ShinySwapItem extends PokemonUseItem {
  public ShinySwapItem() {
    super(new Item.Settings().maxCount(1));
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    boolean isShiny = pokemon.getShiny(); // Determines if Pokémon is shiny

    // Toggle shiny status
    pokemon.setShiny(!isShiny);

    String shinyMessage = isShiny ? "no longer Shiny" : "now Shiny";
    player.sendMessage(Text.of("The Pokémon is " + shinyMessage));
    itemStack.decrement(1); // remove item after use

    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Swap Shiny Status of Pokémon"));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
