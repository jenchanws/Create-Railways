package com.railwayteam.railways.forge.events;

import com.railwayteam.railways.Railways;
import com.railwayteam.railways.content.conductor.ConductorEntity;
import com.railwayteam.railways.content.conductor.toolbox.MountedToolbox;
import com.railwayteam.railways.events.CommonEvents;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber
public class CommonEventsForge {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == Phase.START)
			CommonEvents.onWorldTickStart(event.world);
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getPlayer() instanceof ServerPlayer player)
			CommonEvents.onPlayerJoin(player);
	}

	private static final ResourceLocation conductorItemCap = Railways.asResource("conductor_item_capability");

	@SubscribeEvent
	public static void onCapabilitiesAttach(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof ConductorEntity conductor) {
			event.addCapability(conductorItemCap, new ICapabilityProvider() {
				@NotNull
				@Override
				public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
					if (cap != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
						return LazyOptional.empty();
					MountedToolbox toolbox = conductor.getToolbox();
					if (toolbox == null)
						return LazyOptional.empty();
					return toolbox.getCapability(cap);
				}
			});
		}
	}
}
