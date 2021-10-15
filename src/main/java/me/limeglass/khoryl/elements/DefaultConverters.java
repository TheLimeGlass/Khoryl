package me.limeglass.khoryl.elements;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;

public class DefaultConverters {

	static {
		Converters.registerConverter(AbstractVillager.class, Inventory.class, new Converter<AbstractVillager, Inventory>() {
			@Override
			@Nullable
			public Inventory convert(AbstractVillager villager) {
				return villager.getInventory();
			}
		});
		Converters.registerConverter(Llama.class, LlamaInventory.class, new Converter<Llama, LlamaInventory>() {
			@Override
			@Nullable
			public LlamaInventory convert(Llama llama) {
				return llama.getInventory();
			}
		});
		Converters.registerConverter(ItemStack.class, BlockState.class, new Converter<ItemStack, BlockState>() {
			@Override
			@Nullable
			public BlockState convert(ItemStack item) {
				ItemMeta meta = item.getItemMeta();
				if (!(meta instanceof BlockStateMeta))
					return null;
				BlockStateMeta blockStateMeta = (BlockStateMeta) meta;
				if (!blockStateMeta.hasBlockState())
					return null;
				return blockStateMeta.getBlockState();
			}
		});
		Converters.registerConverter(BlockState.class, Block.class, new Converter<BlockState, Block>() {
			@Override
			@Nullable
			public Block convert(BlockState state) {
				return state.getBlock();
			}
		});
	}
}
