package me.limeglass.khoryl.elements.entity.zombie;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
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

@Name("Zombie Drowned Conversion Time")
@Description("Gets the amount of ticks until this entity will be converted to a Drowned as a result of being underwater.")
@Since("1.0.0")
public class ExprDrownedConversionTime extends EntityPropertyExpression<LivingEntity, Zombie, Timespan> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(ExprDrownedConversionTime.class, Timespan.class, "drown[ed] (conversion|converting) time", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "drowned conversion time";
	}

	@Override
	@Nullable
	protected Timespan grab(Zombie villager) {
		if (!villager.isConverting())
			return null;
		return Timespan.fromTicks_i(villager.getConversionTime());
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.SET || mode == ChangeMode.DELETE)
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
				for (Zombie zombie : getEntities(event)) {
					if (!zombie.isConverting()) {
						zombie.setConversionTime(ticks);
						continue;
					}
					int existing = zombie.getConversionTime();
					zombie.setConversionTime(existing + ticks);
				}
				break;
			case REMOVE:
				for (Zombie zombie : getEntities(event)) {
					if (!zombie.isConverting())
						continue;
					int existing = zombie.getConversionTime();
					zombie.setConversionTime(existing - ticks);
				}
				break;
			case SET:
				for (Zombie zombie : getEntities(event))
					zombie.setConversionTime(ticks);
				break;
			case DELETE:
				for (Zombie zombie : getEntities(event))
					zombie.setConversionTime(0);
				break;
			case REMOVE_ALL:
			case RESET:
			default:
				break;
		}
	}

}
