package cope.cosmos.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

/**
 * @author bon55
 * @since 05/05/2021
 */
@IFMLLoadingPlugin.Name("Cosmos")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class MixinLoader implements IFMLLoadingPlugin {
	private static boolean isObfuscatedEnvironment = false;

	public MixinLoader() {
		MixinBootstrap.init();
		Mixins.addConfiguration("mixins.cosmos.json");
		MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
	}
	
	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		isObfuscatedEnvironment = (boolean) data.get("runtimeDeobfuscationEnabled");
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}