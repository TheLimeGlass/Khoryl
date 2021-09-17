package me.limeglass.khoryl.lang;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class SettableBlockStatePropertyCondition<S extends BlockState> extends SettablePropertyCondition<Block> implements BlockStateSyntax<S> {

	private final boolean printErrors;

	protected SettableBlockStatePropertyCondition() {
		printErrors = Khoryl.getInstance().canRuntimeError();
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

	@SuppressWarnings("unchecked")
	protected S[] getArray(Expression<?> expression, Event event) {
		return (S[]) expression.getArray(event);
	}

	protected static <C extends SettableBlockStatePropertyCondition<?>> void register(Class<C> c, String property, String setproperty, Class<? extends PropertyEffect> effect) {
		register(c, property, setproperty, "blocks", effect);
	}

	protected static <C extends SettableBlockStatePropertyCondition<?>> void register(Class<C> c, PropertyType propertyType, String property, String setproperty, Class<? extends PropertyEffect> effect) {
		register(c, propertyType, property, setproperty, "blocks", effect);
	}

}
