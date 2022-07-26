package me.limeglass.khoryl.lang;

import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.Khoryl;

public abstract class EntityPropertyCondition<P extends Entity> extends PropertyCondition<Entity> implements EntitySyntax<P> {

	private final boolean printErrors;

	public EntityPropertyCondition() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	protected static void register(Class<? extends EntityPropertyCondition<?>> condition, String property) {
		register(condition, property, "entities");
	}

	protected static void register(Class<? extends EntityPropertyCondition<?>> condition, PropertyType propertyType, String property) {
		register(condition, propertyType, property, "entities");
	}

	protected abstract boolean checkEntity(P entity);

	private int matchedPattern;

	protected int getMatchedPattern() {
		return matchedPattern;
	}

	protected boolean negated(boolean provided) {
		return provided;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends P>) exprs[0]);
		setNegated(negated(matchedPattern == 1));
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Entity entity) {
		if (!accepts(entity)) {
			if (printErrors)
				Skript.error("An entity was not of type " + getCastingEntityClass().getName()
						+ " in property condition '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return checkEntity((P)entity);
	}

}
