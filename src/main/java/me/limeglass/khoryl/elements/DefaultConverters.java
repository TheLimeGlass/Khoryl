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
import org.skriptlang.skript.lang.converter.Converters;

public class DefaultConverters {

	static {
		Converters.registerConverter(AbstractVillager.class, Inventory.class, villager -> villager.getInventory());

		Converters.registerConverter(Llama.class, LlamaInventory.class, llama -> llama.getInventory());

		Converters.registerConverter(ItemStack.class, BlockState.class, item -> {
            ItemMeta meta = item.getItemMeta();
            if (!(meta instanceof BlockStateMeta))
                return null;
            BlockStateMeta blockStateMeta = (BlockStateMeta) meta;
            if (!blockStateMeta.hasBlockState())
                return null;
            return blockStateMeta.getBlockState();
        });

		Converters.registerConverter(BlockState.class, Block.class, state -> state.getBlock());
	}

}
