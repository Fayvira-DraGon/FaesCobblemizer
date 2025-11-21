package git.dragomordor.cobblemizer.fabric.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static git.dragomordor.cobblemizer.fabric.CobblemizerMod.*;

public class ModComponents {
  public static final ComponentType<String> SPECIES_COMPONENT = Registry.register(
    Registries.DATA_COMPONENT_TYPE,
    of("species_component"),
    ComponentType.<String>builder().codec(Codec.STRING).build()
  );

  public static void registerDataComponentTypes() {
    LOGGER.info("Register Data Component Types for " + MOD_NAME);
  }
}
