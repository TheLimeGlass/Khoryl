package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;

@Name("Freeze Ticks")
@Description("Returns the amount of time an entity has been frozen in powdered snow.")
@Examples("set {_ticks} to frozen time of target entity")
@Since("1.0.4")
public class ExprFreezeTicks extends SimplePropertyExpression<Entity, Timespan> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprFreezeTicks.class, Timespan.class, "(frozen|freez(e|ing)) (time|tick[s])", "entities");
	}

	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}

	@Override
	protected String getPropertyName() {
		return "freeze ticks";
	}

	@Override
	@Nullable
	public Timespan convert(Entity entity) {
		return Timespan.fromTicks_i(entity.getFreezeTicks());
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.SET)
			return CollectionUtils.array(Timespan.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int ticks = (int) ((Timespan) delta[0]).getTicks_i();
		switch (mode) {
			case ADD:
				for (Entity entity : getExpr().getArray(event)) {
					int existing = entity.getFreezeTicks();
					entity.setFreezeTicks(existing + ticks);
				}
				break;
			case REMOVE:
				for (Entity entity : getExpr().getArray(event)) {
					int existing = entity.getFreezeTicks();
					entity.setFreezeTicks(existing - ticks);
				}
				break;
			case SET:
				for (Entity entity : getExpr().getArray(event))
					entity.setFreezeTicks(ticks);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

}
