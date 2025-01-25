package git.dragomordor.cobblemizer.fabric.item.custom;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
// import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class ShinySwapItem extends PokemonUseItem{
    public ShinySwapItem() {
        // super(new FabricItemSettings().maxCount(1));
        super(new Item.Settings().maxCount(1));
    }

    @Override
    public ActionResult processInteraction(ItemStack itemStack, PlayerEntity player, PokemonEntity target, Pokemon pokemon) {
        boolean isShiny = pokemon.getShiny(); // Determines if Pokémon is shiny

        // Toggle shiny status
        pokemon.setShiny(!isShiny);

        String shinyMessage = isShiny ? "no longer shiny" : "now shiny";
        player.sendMessage(Text.of("The Pokémon is " + shinyMessage));
        itemStack.decrement(1); // remove item after use

        return ActionResult.SUCCESS;
    }
}
