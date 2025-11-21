package git.dragomordor.cobblemizer.fabric;

import git.dragomordor.cobblemizer.fabric.component.ModComponents;
import git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig;
import git.dragomordor.cobblemizer.fabric.item.CobblemizerItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CobblemizerMod implements ModInitializer {
  public static final Logger LOGGER = LogManager.getLogger(CobblemizerMod.class); // create logger
  public static final String MOD_ID = "cobblemizer"; // mod ID
  public static final String MOD_NAME = "Fae's Cobblemizer"; // mod ID

  @Override
  public void onInitialize() {
    // Always load config file first
    CobblemizerConfig.Builder.load();
    // Register mod components
    ModComponents.registerDataComponentTypes();
    // Register all items
    CobblemizerItems.registerModItems();

  }

  public static Identifier of(String name) {
    return Identifier.of(MOD_ID, name);
  }
}
