package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class OTSwapItem extends PokemonUseItem {
  public OTSwapItem() {
    super(new Item.Settings().maxCount(1));
  }

  @Override
  public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
    pokemon.removeOriginalTrainer(); // Remove Pokémon's original trainer
    pokemon.setOriginalTrainer(player.getUuid()); // Set the Original Trainer via UUID.
    pokemon.refreshOriginalTrainer(); // Refresh the Original Trainer Data to apply the change to name & type.
    player.sendMessage(Text.of("Pokémon's Original Trainer is now " + pokemon.getOriginalTrainerName() + "."));
    itemStack.decrement(1); // remove item after use

    return ActionResult.SUCCESS;
  }
}
