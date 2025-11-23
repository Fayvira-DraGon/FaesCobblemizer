package git.dragomordor.cobblemizer.fabric.item;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static git.dragomordor.cobblemizer.fabric.Cobblemizer.*;

public class ItemComponents {
  public static final ComponentType<String> SPECIES_NAME_COMPONENT = Registry.register(
    Registries.DATA_COMPONENT_TYPE,
    of("species_name_component"),
    ComponentType.<String>builder().codec(Codec.STRING).build()
  );

  public static void registerItemComponents() {
    LOGGER.info("Register Item Components for " + MOD_NAME);
  }
}
