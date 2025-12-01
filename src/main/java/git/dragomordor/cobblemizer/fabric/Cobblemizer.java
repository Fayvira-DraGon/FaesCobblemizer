package git.dragomordor.cobblemizer.fabric;

import git.dragomordor.cobblemizer.fabric.item.ItemComponents;
import git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig;
import git.dragomordor.cobblemizer.fabric.item.CobblemizerItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cobblemizer implements ModInitializer {
  public static final Logger LOGGER = LogManager.getLogger(Cobblemizer.class); // create logger
  public static final String MOD_ID = "cobblemizer"; // mod ID
  public static final String MOD_NAME = "Fae's Cobblemizer"; // mod ID

  //TODO set the config values on modMenu update

  @Override
  public void onInitialize() {
    // Always load config file first
    CobblemizerConfig.Builder.load();
    // Register mod components
    ItemComponents.registerItemComponents();
    // Register all items
    CobblemizerItems.registerModItems();
  }

  public static Identifier of(String name) {
    return Identifier.of(MOD_ID, name);
  }
}
