package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public class ModKeyBindings {

    protected static final List<KeyMapping> MAPPINGS = new ArrayList<>();

    public static final KeyMapping SPECIAL_ABILITY_KEY = register("special_ability_key", GLFW.GLFW_KEY_R, KeyMapping.CATEGORY_MISC);

    /**
     *
     * @param name
     * @param keyCode
     * @return
     */
    public static KeyMapping register(String name, int keyCode, String category) {
        KeyMapping mapping = new KeyMapping("key." + Ornithodira.MODID + "." + name, keyCode, category);
        MAPPINGS.add(mapping);
        return mapping;
    }

    public static void registerKeyBindings(RegisterKeyMappingsEvent event){
        MAPPINGS.forEach(event::register);
    }
}
