package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Validate;
import me.limeglass.khoryl.Khoryl;

public abstract class EntityExpression<F extends Entity, P extends Entity, T> extends SimpleExpression<T> implements EntitySyntax<P> {

	private final boolean printErrors;
	private Expression<F> entities;

	public EntityExpression() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	protected void setEntities(Expression<F> entities) {
		this.entities = entities;
	}

	protected Expression<F> getEntitiesExpression() {
		return entities;
	}

	@SuppressWarnings({"serial", "unchecked"})
	@Override
	public Class<? extends T> getReturnType() {
		return (Class<? extends T>) new TypeToken<T>(getClass()){}.getRawType();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected @Nullable T[] get(Event event) {
		Validate.notNull(entities, "The entities expression cannot be null, use setEntities(entities)");
		return (T[]) Arrays.stream(entities.getArray(event))
				.flatMap(entity -> {
					if (!accepts(entity)) {
						if (printErrors)
							Skript.error("An entity was not of type " + getCastingEntityClass().getName()
								+ " in expression '" + getClass().getName() + "'", ErrorQuality.SEMANTIC_ERROR);
						return null;
					}
					return get(event, (P) entity);
				})
				.toArray();
	}

	@Nullable
	protected abstract Stream<T> get(Event event, P entity);

	@SuppressWarnings("unchecked")
	protected Set<P> getEntities(Event event) {
		Validate.notNull(entities, "The entities expression cannot be null, use setEntities(entities)");
		return Arrays.stream(entities.getArray(event))
				.filter(entity -> accepts(entity))
				.map(entity -> (P) entity)
				.collect(Collectors.toSet());
	}

}
