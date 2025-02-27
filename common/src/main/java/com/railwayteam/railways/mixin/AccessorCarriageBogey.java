package com.railwayteam.railways.mixin;

import com.simibubi.create.content.logistics.trains.IBogeyBlock;
import com.simibubi.create.content.logistics.trains.entity.CarriageBogey;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = CarriageBogey.class, remap = false)
public interface AccessorCarriageBogey {
    @Accessor
    IBogeyBlock getType();

    @Accessor("isLeading")
    boolean isLeading();

    @Accessor
    LerpedFloat getYaw();

    @Accessor
    LerpedFloat getPitch();
}
