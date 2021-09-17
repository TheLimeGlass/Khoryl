package me.limeglass.khoryl.lang;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public abstract class SetEffect<T> extends Effect {

	private Expression<Boolean> value;
	private Expression<T> expression;

	public static void register(Class<? extends SetEffect<?>> effect, String property) {
		Skript.registerEffect(effect, "set " + property + " of %blocks% to %boolean%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (exprs.length != 2)
			Skript.error("There was not two expressions in the SetEffect class " + getClass().getName() + " exprs: " + Arrays.toString(exprs));
		expression = (Expression<T>) exprs[0];
		value = (Expression<Boolean>) exprs[1];
		return true;
	}

	protected boolean getBoolean(Event event) {
		return value.getSingle(event);
	}

	protected Expression<T> getExpression() {
		return expression;
	}

	protected abstract String getPropertyName();

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "setting " + getPropertyName();
		return "set " + getPropertyName() + " of " + expression.toString(event, debug) + " to " + value.toString(event, debug);
	}

}
