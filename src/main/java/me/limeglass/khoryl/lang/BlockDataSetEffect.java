package me.limeglass.khoryl.lang;

import java.util.function.BiConsumer;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import ch.njol.skript.Skript;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockDataSetEffect<S extends BlockData> extends SetEffect<Block> implements BlockDataSyntax<S> {

	protected final boolean printErrors;

	public BlockDataSetEffect() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	protected static void register(Class<? extends SetEffect<?>> effect, String property) {
		register(effect, property, "blocks");
	}

	protected abstract S updateBlockData(S data, boolean value);

	@SuppressWarnings("unchecked")
	@Override
	protected BiConsumer<Block, Boolean> apply() {
		return (block, value) -> {
			BlockData data = block.getBlockData();
			if (!accepts(data)) {
				if (printErrors)
					Skript.error("A block data was not of type " + getCastingBlockDataClass().getName()
							+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
				return;
			}
			block.setBlockData(updateBlockData((S) data, value));
		};
	}

}
