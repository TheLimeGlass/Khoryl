package me.limeglass.khoryl.elements.entity.zombie.villager;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Timespan;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Zombie Villager Conversion Time")
@Description("Gets the amount of ticks until this entity will be converted to a Villager as a result of being cured.")
@Since("1.0.0")
public class ExprVillagerConversionTime extends EntityPropertyExpression<LivingEntity, ZombieVillager, Timespan> {

	static {
		register(ExprVillagerConversionTime.class, Timespan.class, "[[zombie] villager] (conversion|converting) time", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "villager conversion time";
	}

	@Override
	@Nullable
	protected Timespan grab(ZombieVillager villager) {
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
				for (ZombieVillager villager : getEntities(event)) {
					if (!villager.isConverting()) {
						villager.setConversionTime(ticks);
						continue;
					}
					int existing = villager.getConversionTime();
					villager.setConversionTime(existing + ticks);
				}
				break;
			case REMOVE:
				for (ZombieVillager villager : getEntities(event)) {
					if (!villager.isConverting())
						continue;
					int existing = villager.getConversionTime();
					villager.setConversionTime(existing - ticks);
				}
				break;
			case SET:
				for (ZombieVillager villager : getEntities(event))
					villager.setConversionTime(ticks);
				break;
			case DELETE:
				for (ZombieVillager villager : getEntities(event))
					villager.setConversionTime(0);
				break;
			case REMOVE_ALL:
			case RESET:
			default:
				break;
		}
	}

}
