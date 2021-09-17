package me.limeglass.khoryl.lang;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class SettableBlockDataPropertyCondition<S extends BlockData> extends SettablePropertyCondition<Block> implements BlockDataSyntax<S> {

	private final boolean printErrors;

	protected SettableBlockDataPropertyCondition() {
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

	@SuppressWarnings("unchecked")
	protected S[] getArray(Expression<?> expression, Event event) {
		return (S[]) expression.getArray(event);
	}

	protected static <C extends SettableBlockDataPropertyCondition<?>> void register(Class<C> c, String property, String setproperty, Class<? extends PropertyEffect> effect) {
		register(c, property, setproperty, "blocks", effect);
	}

	protected static <C extends SettableBlockDataPropertyCondition<?>> void register(Class<C> c, PropertyType propertyType, String property, String setproperty, Class<? extends PropertyEffect> effect) {
		register(c, propertyType, property, setproperty, "blocks", effect);
	}

}
