package cope.cosmos.asm.mixins.render;

import cope.cosmos.client.Cosmos;
import cope.cosmos.client.events.render.world.RenderCaveCullingEvent;
import cope.cosmos.client.features.modules.visual.Xray;
import net.minecraft.client.renderer.chunk.SetVisibility;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VisGraph.class)
public class MixinVisGraph {

    @Inject(method = "computeVisibility", at = @At("HEAD"), cancellable = true)
    public void onComputeVisibility(CallbackInfoReturnable<SetVisibility> info) {
        if (Cosmos.EVENT_BUS.post(new RenderCaveCullingEvent())) {
            SetVisibility setVisibility = new SetVisibility();
            setVisibility.setAllVisible(true);

            info.setReturnValue(setVisibility);
        }
    }

    @Inject(method = "setOpaqueCube", at = @At("HEAD"), cancellable = true)
    public void setOpaqueCube(BlockPos pos, CallbackInfo ci) {
        if (Xray.INSTANCE.isEnabled()) {
            ci.cancel();
        }
    }
}