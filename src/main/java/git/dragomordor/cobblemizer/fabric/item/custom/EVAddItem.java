package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.api.pokemon.stats.ItemEvSource;
import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;
import java.util.Map.Entry;

import static git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig.EV_TIERS;
import static git.dragomordor.cobblemizer.fabric.config.TierRarityClass.getTierAmount;

public class EVAddItem extends PokemonUseItem {
  private final String tier;
  private final Stat statToBoost;

  public EVAddItem(String tier, Stat statToBoost) {
    super(new Settings().maxCount(1));
    this.tier = tier;
    this.statToBoost = statToBoost;
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    EVs evs = pokemon.getEvs(); // Current EV values
    int tierAmount = getTierAmount(EV_TIERS, tier); // Get tierAmount from config
    int actualIncrease = evs.add(statToBoost, tierAmount); // Increase EV
    int evAmount = evs.get(this.statToBoost) instanceof Integer evBoxed ? evBoxed : 0; // Get unboxed EV amount

    if (actualIncrease == 0) { // If EV or EV Total are already at max, return pass
      int evTotal = 0;
      for (Entry<? extends Stat, ? extends Integer> stat : evs)
        evTotal += (stat.getValue() instanceof Integer evBoxed ? evBoxed : 0);
      player.sendMessage(Text.of("Pokémon already has the maximum " + (evTotal == EVs.MAX_TOTAL_VALUE ? "EV Total" : statToBoost.getDisplayName().getString() + " EV")));
      return ActionResult.PASS;
    }

    player.sendMessage(Text.of("Increased Pokémon's " + statToBoost.getDisplayName().getString() + " EV by " + actualIncrease + " to " + evAmount));
    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Increase Pokémon's " + statToBoost.getDisplayName().getString() + " EV by up to " + getTierAmount(EV_TIERS, tier)));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
