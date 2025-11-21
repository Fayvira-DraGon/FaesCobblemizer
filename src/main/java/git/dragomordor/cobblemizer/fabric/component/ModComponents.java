package git.dragomordor.cobblemizer.fabric.component;

import com.mojang.serialization.Codec;
import git.dragomordor.cobblemizer.fabric.CobblemizerMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModComponents {
  public static final ComponentType<String> SPECIES_COMPONENT = Registry.register(
    Registries.DATA_COMPONENT_TYPE,
    CobblemizerMod.of("species_component"),
    ComponentType.<String>builder().codec(Codec.STRING).build()
  );

  public static void registerDataComponentTypes() {
    CobblemizerMod.LOGGER.info("Register Data Component Types for " + CobblemizerMod.MOD_NAME);
  }
}
