package me.limeglass.khoryl.elements.entity.steerable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Steerable;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Current Steerable Boost")
@Description("Get the current boost time of a steerable entity such as a pig or strider. This can be added, set or removed.")
@Since("1.0.4")
public class ExprCurrentBoost extends EntityPropertyExpression<Entity, Steerable, Timespan> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 16)))
			register(ExprCurrentBoost.class, Timespan.class, "current [steerable] boost [time]", "entities");
	}

	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}

	@Override
	protected String getPropertyName() {
		return "current steerable boost time";
	}

	@Override
	@Nullable
	public Timespan grab(Steerable steerable) {
		return Timespan.fromTicks_i(steerable.getCurrentBoostTicks());
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
				for (Steerable steerable : getEntities(event)) {
					int existing = steerable.getCurrentBoostTicks();
					steerable.setCurrentBoostTicks(existing + ticks);
				}
				break;
			case REMOVE:
				for (Steerable steerable : getEntities(event)) {
					int existing = steerable.getCurrentBoostTicks();
					steerable.setCurrentBoostTicks(existing - ticks);
				}
				break;
			case SET:
				for (Steerable steerable : getEntities(event))
					steerable.setCurrentBoostTicks(ticks);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

}
