package me.limeglass.khoryl.lang;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class EntitySetEffect<E extends Entity> extends SetEffect<Entity> implements EntitySyntax<E> {

	private final boolean printErrors;

	public EntitySetEffect() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	protected static void register(Class<? extends SetEffect<?>> effect, String property) {
		register(effect, property, "entities");
	}

	public abstract void apply(E entity, boolean boo);

	@Override
	protected void execute(Event event) {
		apply(event, (entity, boo) -> apply(convert(entity), boo));
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public final E convert(Entity entity) {
		if (entity == null)
			return null;
		if (!accepts(entity)) {
			if (printErrors)
				Skript.error("An entity was not of type " + getCastingEntityClass().getName()
						+ " in set effect '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return null;
		}
		return (E) entity;
	}

}
