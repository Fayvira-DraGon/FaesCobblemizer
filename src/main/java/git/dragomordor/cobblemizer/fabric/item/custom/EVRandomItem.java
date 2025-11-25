package git.dragomordor.cobblemizer.fabric.item.custom;


import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;
import java.util.Random;

public class EVRandomItem extends PokemonUseItem {

  public EVRandomItem() {
    super(new Item.Settings().maxCount(1));
  }

  // TODO: fix ignored total EVs

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    EVs evs = pokemon.getEvs(); // Access the EVs of the Pokémon
    Random random = new Random(); // random number generator

    // Randomize all EV stats
    for (Stat stat : Stats.values()) {
      int randomValue = random.nextInt(EVs.MAX_STAT_VALUE + 1); // Generate a random value between 0 and MAX_STAT_VALUE (inclusive)
      evs.set(stat, randomValue); // Set each EV stat to the generated random value
    }

    itemStack.decrement(1); // remove item after use
    player.sendMessage(Text.of("All EVs randomized"));
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Randomize All EVs of a Pokémon"));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
