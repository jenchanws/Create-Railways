package com.railwayteam.railways.mixin;

import com.railwayteam.railways.mixin_interfaces.IHasTrackCasing;
import com.simibubi.create.content.logistics.trains.track.TrackBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BlockBehaviour.class)
public class MixinBlockBehaviour {
  /**
   * @author Railways
   * @reason Can't inject in MixinTrackBlock, because TrackBlock itself doesn't include this method, only its superclass (BlockBehaviour)
   */
//  @Inject(method = "getDrops", at = @At("RETURN"), cancellable = true)
  private void addTrackCasingDrops(BlockState state, LootContext.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
    List<ItemStack> superList = cir.getReturnValue();
    if (builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY) instanceof IHasTrackCasing casing && casing.getTrackCasing() != null) {
      superList.add(new ItemStack(casing.getTrackCasing()));
    }
    cir.setReturnValue(superList);
  }
}
