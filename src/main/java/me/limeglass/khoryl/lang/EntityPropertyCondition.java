package me.limeglass.khoryl.lang;

import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class EntityPropertyCondition<F extends Entity, P extends Entity> extends PropertyCondition<F> implements EntitySyntax<P> {

	private final boolean printErrors;

	public EntityPropertyCondition() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	protected abstract boolean checkEntity(P entity);

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(F entity) {
		if (!accepts(entity)) {
			if (printErrors)
				Skript.error("An entity was not of type " + getCastingEntityClass().getName()
						+ " in property condition '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return checkEntity((P)entity);
	}

}
