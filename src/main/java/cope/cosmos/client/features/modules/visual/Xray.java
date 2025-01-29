package cope.cosmos.client.features.modules.visual;

import cope.cosmos.client.features.modules.Category;
import cope.cosmos.client.features.modules.Module;
import net.minecraft.block.state.IBlockState;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Xray extends Module
{
    public static Xray INSTANCE;

    private static final Set<String> defaultVisibleList = new LinkedHashSet<>(Arrays.asList(
            "minecraft:diamond_ore",
            "minecraft:iron_ore",
            "minecraft:gold_ore",
            "minecraft:portal",
            "minecraft:cobblestone"
    ));


    public Xray() {
        super("Xray", new String[] {}, Category.VISUAL,"XRay Vision");
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        mc.renderGlobal.loadRenderers();
    }

    @Override
    public void onDisable() {
        mc.renderGlobal.loadRenderers();
    }

    public static boolean shouldReplace(IBlockState blockState) {
        return INSTANCE.isEnabled() && !defaultVisibleList.contains(blockState.getBlock().getRegistryName().toString());
    }
}
