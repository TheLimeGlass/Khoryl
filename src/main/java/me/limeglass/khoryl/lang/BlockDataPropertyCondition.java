package me.limeglass.khoryl.lang;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockDataPropertyCondition<S extends BlockData> extends PropertyCondition<Block> implements BlockDataSyntax<S> {

	private final boolean printErrors;

	public BlockDataPropertyCondition() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	protected abstract boolean checkBlockData(S data);

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Block block) {
		BlockData data = block.getBlockData();
		if (!accepts(data)) {
			if (printErrors)
				Skript.error("A block data was not of type " + getCastingBlockDataClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return checkBlockData((S)data);
	}

}
