package git.dragomordor.cobblemizer.fabric;

import git.dragomordor.cobblemizer.fabric.config.CobblemizerConfig;
import git.dragomordor.cobblemizer.fabric.item.CobblemizerItems;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CobblemizerMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(CobblemizerMod.class); // create logger
    public static final String MODID = "cobblemizer"; // mod ID
    @Override
    public void onInitialize() {
        // Always load config file first
        CobblemizerConfig.Builder.load();

        // Register all items
        CobblemizerItems.registerModItems();

    }
}
