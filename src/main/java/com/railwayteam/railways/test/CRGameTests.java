package com.railwayteam.railways.test;

import com.railwayteam.railways.Railways;
import com.railwayteam.railways.registry.CRBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder(Railways.MODID)
public class CRGameTests {
    @GameTest
    public static void exampleTest(GameTestHelper helper) {
        final BlockPos spawn = new BlockPos(4, 2, 1);
        final BlockPos power = new BlockPos(3, 2, 1);
        final BlockPos lamp = new BlockPos(4, 2, 9);
        helper.spawn(EntityType.MINECART, spawn);
        helper.setBlock(power, Blocks.REDSTONE_BLOCK);
        helper.succeedWhen(() -> {
            helper.assertBlockProperty(lamp, RedstoneLampBlock.LIT, true);
        });
        BlockPos absSpawn = helper.absolutePos(spawn);
        Player mockPlayer = helper.makeMockPlayer();
        mockPlayer.teleportTo(absSpawn.getX(), absSpawn.getY(), absSpawn.getZ());
        ItemStack stoneStack = new ItemStack(AllBlocks.TRACK.get(), 16);
        mockPlayer.setItemInHand(InteractionHand.MAIN_HAND, stoneStack);
        stoneStack.useOn(new UseOnContext(mockPlayer, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atBottomCenterOf(absSpawn).add(0, 1, 0), Direction.UP, absSpawn, false)));
    }

    @GameTest(template = "track_place_break_standard")
    public static void placeBreakTrackStandard(GameTestHelper helper) {
        final BlockPos rail1 = new BlockPos(5, 4, 2);
        final BlockPos rail1Abs = helper.absolutePos(rail1);
        final BlockPos stand1 = helper.absolutePos(new BlockPos(5, 2, 0));

        final BlockPos rail2 = new BlockPos(10, 4, 13);
        final BlockPos rail2Abs = helper.absolutePos(rail2);
        final BlockPos stand2 = helper.absolutePos(new BlockPos(10, 2, 15));

        final BlockPos output = new BlockPos(7, 1, 8);

        Player mockPlayer = helper.makeMockPlayer();
        mockPlayer.teleportTo(stand1.getX(), stand1.getY(), stand1.getZ());
        ItemStack trackStack = new ItemStack(AllBlocks.TRACK.get(), 64);
        mockPlayer.setItemInHand(InteractionHand.MAIN_HAND, trackStack);
        trackStack.useOn(new UseOnContext(mockPlayer, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atBottomCenterOf(rail1Abs).add(0, 0.1, 0), Direction.UP, rail1Abs, false)));

        mockPlayer.teleportTo(stand2.getX(), stand2.getY(), stand2.getZ());
        trackStack.useOn(new UseOnContext(mockPlayer, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atBottomCenterOf(rail2Abs).add(0, 0.1, 0), Direction.UP, rail2Abs, false)));

        helper.destroyBlock(rail1);
        helper.destroyBlock(rail2);

        helper.succeedWhen(() -> {
            BlockEntity outputTE = helper.getBlockEntity(output);
            if (!(outputTE instanceof HopperBlockEntity hopper) || hopper.countItem(AllBlocks.TRACK.get().asItem()) != 12) {
                throw new GameTestAssertException("Wrong output contents");
            }
        });
    }

    @GameTest(template = "track_place_break_standard")
    public static void placeBreakTrackMonorail(GameTestHelper helper) {
        final BlockPos rail1 = new BlockPos(5, 4, 2);
        final BlockPos rail1Abs = helper.absolutePos(rail1);
        final BlockPos stand1 = helper.absolutePos(new BlockPos(5, 2, 0));

        final BlockPos rail2 = new BlockPos(10, 4, 13);
        final BlockPos rail2Abs = helper.absolutePos(rail2);
        final BlockPos stand2 = helper.absolutePos(new BlockPos(10, 2, 15));

        final BlockPos output = new BlockPos(7, 1, 8);

        Player mockPlayer = helper.makeMockPlayer();
        mockPlayer.teleportTo(stand1.getX(), stand1.getY(), stand1.getZ());
        ItemStack trackStack = new ItemStack(CRBlocks.MONORAIL_TRACK.get(), 64);
        mockPlayer.setItemInHand(InteractionHand.MAIN_HAND, trackStack);
        trackStack.useOn(new UseOnContext(mockPlayer, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atBottomCenterOf(rail1Abs).add(0, 0.1, 0), Direction.UP, rail1Abs, false)));

        mockPlayer.teleportTo(stand2.getX(), stand2.getY(), stand2.getZ());
        trackStack.useOn(new UseOnContext(mockPlayer, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atBottomCenterOf(rail2Abs).add(0, 0.1, 0), Direction.UP, rail2Abs, false)));

        helper.destroyBlock(rail1);
        helper.destroyBlock(rail2);

        helper.succeedWhen(() -> {
            BlockEntity outputTE = helper.getBlockEntity(output);
            if (!(outputTE instanceof HopperBlockEntity hopper) || hopper.countItem(CRBlocks.MONORAIL_TRACK.get().asItem()) != 12) {
                throw new GameTestAssertException("Wrong output contents");
            }
        });
    }
}
