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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EVRandomItem extends PokemonUseItem {

  public EVRandomItem() {
    super(new Item.Settings().maxCount(1));
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    Random random = new Random(); // random number generator
    List<Stat> statsList = new ArrayList<>();
    Collections.addAll(statsList, Stats.values()); // add the Stats to the Stat List

    while (!statsList.isEmpty()) { // Loop randomly through the Stat List until there are no elements left
      int stat = random.nextInt(statsList.size()); // Generate a random value between 0 & the size of the statsList (exclusive)
      int randomValue = random.nextInt(EVs.MAX_STAT_VALUE + 1); // Generate a random value between 0 and MAX_STAT_VALUE (inclusive)

      if (pokemon.getEvs().get(statsList.get(stat)) instanceof Integer) {
        pokemon.setEV(statsList.get(stat), randomValue); // Set the NonNull EV stat to the generated random value
      }

      player.sendMessage(Text.of(statsList.get(stat).getDisplayName().getString() + " EV: " + pokemon.getEvs().get(statsList.get(stat))));
      statsList.remove(stat); // Remove the randomly generated stat entry from the statsList
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
