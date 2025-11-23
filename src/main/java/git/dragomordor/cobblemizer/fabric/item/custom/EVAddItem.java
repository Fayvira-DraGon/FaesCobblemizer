package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig;
import git.dragomordor.cobblemizer.fabric.config.TierRarityClass;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

import static git.dragomordor.cobblemizer.fabric.Cobblemizer.LOGGER;

public class EVAddItem extends PokemonUseItem {
  private final String tier;
  private final Stat statToBoost;

  public EVAddItem(String tier, Stat statToBoost) {
    super(new Item.Settings().maxCount(1));
    this.tier = tier;
    this.statToBoost = statToBoost;
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    CobblemizerConfig config = CobblemizerConfig.Builder.load();
    int maxEV = EVs.MAX_STAT_VALUE; // Maximum EV value
    EVs evs = pokemon.getEvs(); // Current EV values
    // Get the increaseAmount from the config based on the provided tier
    int increaseAmount = getIncreaseAmountForTier(config, tier);
    // int EVcurrentAmount = evs.get(this.statToBoost);
    Integer currentEvBoxed = evs.get(this.statToBoost);
    if(currentEvBoxed == null) {
      LOGGER.warn("Invalid stat for item: {}", this.getClass().getSimpleName());
      player.sendMessage(Text.of("Invalid EV associated w/ item."));
      return ActionResult.FAIL;
    }
    int EVcurrentAmount = currentEvBoxed;
    // Modify the Pokémon's EV by the obtained increaseAmount
    int newEVAmount = Math.min(EVcurrentAmount + increaseAmount, maxEV);
    int actualIncrease = newEVAmount - EVcurrentAmount;

    if (actualIncrease <= 0) { // If EV is already at max, return fail
      player.sendMessage(Text.of((statToBoost.getDisplayName().getString() + " EV is already at maximum")));
      return ActionResult.FAIL;
    }

    // if EV not max, increase by tier amount
    evs.add(statToBoost, actualIncrease);
    player.sendMessage(Text.of("Increased Pokémon's " + statToBoost.getDisplayName().getString() + " EV by " + actualIncrease));
    if (newEVAmount == maxEV) { // if new EV amount is maxed, indicate to player
      player.sendMessage(Text.of("Pokémon's " + statToBoost.getDisplayName().getString() + " EV is now at maximum"));
    }
    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  // Method to get the increaseAmount from the config based on the provided tier
  private int getIncreaseAmountForTier(CobblemizerConfig config, String tierName) {
    for (TierRarityClass tier : config.EVTiers) {
      if (tier.name.equalsIgnoreCase(tierName)) {
        return tier.increaseAmount;
      }
    }
    return 0; // Default value if tierName not found in config
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    CobblemizerConfig config = CobblemizerConfig.Builder.load();

    list.add(Text.of("Increase Pokémon's " + statToBoost.getDisplayName().getString() + " EV by up to " + getIncreaseAmountForTier(config, tier)));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
