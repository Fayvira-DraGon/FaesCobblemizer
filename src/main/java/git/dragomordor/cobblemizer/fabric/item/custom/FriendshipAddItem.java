package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

import static git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig.FRIENDSHIP_TIERS;
import static git.dragomordor.cobblemizer.fabric.config.TierRarityClass.getTierAmount;

public class FriendshipAddItem extends PokemonUseItem {
  private final String tier;


  public FriendshipAddItem(String tier) {
    super(new Settings().maxCount(1));
    this.tier = tier;
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    int friendship = pokemon.getFriendship(); // Get Pokémon's Friendship
    int tierAmount = getTierAmount(FRIENDSHIP_TIERS, tier); // Get tierAmount from config

    pokemon.incrementFriendship(friendship + tierAmount, true); // Increase friendship

    if (pokemon.getFriendship() == friendship) { // If Friendship is already at max, return pass
      player.sendMessage(Text.of("Pokémon already has the maximum friendship"));
      return ActionResult.PASS;
    }

    player.sendMessage(Text.of("Increased Pokémon's Friendship by " + (pokemon.getFriendship() - friendship) + " to " + pokemon.getFriendship()));

    itemStack.decrement(1); // remove item after use
    return ActionResult.SUCCESS;
  }

  @Override
  public void appendTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list, TooltipType tooltipType) {
    list.add(Text.of("Increase Pokémon's Friendship by up to " + getTierAmount(FRIENDSHIP_TIERS, tier)));

    super.appendTooltip(itemStack, tooltipContext, list, tooltipType);
  }
}
