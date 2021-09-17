package me.limeglass.khoryl.lang;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAPIException;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public abstract class SettablePropertyCondition<T> extends PropertyCondition<T> {

	protected static abstract class PropertyEffect extends Effect {

		private Expression<Boolean> value;
		private Expression<?> expression;

		@SuppressWarnings("unchecked")
		@Override
		public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
			if (exprs.length != 2)
				Skript.error("There was no two expressions in the PropertyEffect class " + Arrays.toString(exprs));
			expression = (Expression<?>) exprs[0];
			value = (Expression<Boolean>) exprs[1];
			return true;
		}

		public boolean getBoolean(Event event) {
			return value.getSingle(event);
		}

		public Expression<?> getExpression() {
			return expression;
		}

		@Override
		public String toString(@Nullable Event event, boolean debug) {
			return "A PropertyEffect with data: " + expression.toString(event, debug);
		}

	}

	public static <C extends SettablePropertyCondition<?>> void register(Class<C> c, String property, String setproperty, String type, Class<? extends PropertyEffect> effect) {
		register(c, PropertyType.BE, property, setproperty, type, effect);
	}

	public static <C extends SettablePropertyCondition<?>> void register(Class<C> c, PropertyType propertyType, String property, String setproperty, String type, Class<? extends PropertyEffect> effect) {
		if (type.contains("%") || property.contains("%"))
			throw new SkriptAPIException("The type argument must not contain any '%'s");
		PropertyCondition.register(c, propertyType, property, type);
		Skript.registerEffect(effect, "set " + type + " of %" + setproperty + "% to %boolean%");
	}

}
