package me.limeglass.khoryl.lang;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockStatePropertyCondition<S extends BlockState> extends PropertyCondition<Block> implements BlockStateSyntax<S> {

	private final boolean printErrors;

	public BlockStatePropertyCondition() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static void register(Class<? extends BlockStatePropertyCondition<?>> condition, String property) {
		register(condition, property, "blocks");
	}

	protected abstract boolean checkBlockState(S state);

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Block block) {
		BlockState state = block.getState();
		if (!accepts(state)) {
			if (printErrors)
				Skript.error("A block state was not of type " + getCastingBlockStateClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return checkBlockState((S)state);
	}

}
