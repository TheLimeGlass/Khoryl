package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class EntityPropertyExpression<F extends Entity, P extends Entity, T> extends SimplePropertyExpression<F, T> implements EntitySyntax<P> {

	private final boolean printErrors;

	public EntityPropertyExpression() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static <T> void register(Class<? extends Expression<T>> c, Class<T> type, String property, String t) {
		SimplePropertyExpression.register(c, type, property, t);
	}

	public static <T> void register(Class<? extends Expression<T>> c, Class<T> type, String property) {
		SimplePropertyExpression.register(c, type, property, "entities");
	}

	@SuppressWarnings({"serial", "unchecked"})
	@Override
	public Class<? extends T> getReturnType() {
		return (Class<? extends T>) new TypeToken<T>(getClass()){}.getRawType();
	}

	@Nullable
	protected abstract T grab(P entity);

	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public final T convert(F entity) {
		if (entity == null)
			return null;
		if (!accepts(entity)) {
			if (printErrors)
				Skript.error("An entity was not of type " + getCastingEntityClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return null;
		}
		return grab((P)entity);
	}

	@SuppressWarnings("unchecked")
	protected Set<P> getEntities(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.filter(entity -> accepts(entity))
				.map(entity -> (P)entity)
				.collect(Collectors.toSet());
	}

}
