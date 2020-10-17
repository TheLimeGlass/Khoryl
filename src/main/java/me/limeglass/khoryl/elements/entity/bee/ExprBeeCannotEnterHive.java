package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Bee Cannot Enter Hive")
@Description("Gets the ticks of how long bee(s) cannot enter hives.")
@Since("1.0.2")
public class ExprBeeCannotEnterHive extends EntityPropertyExpression<LivingEntity, Bee, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 15)))
			register(ExprBeeCannotEnterHive.class, Number.class, "cannot enter hive ticks", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "cannot enter hive ticks";
	}

	@Override
	@Nullable
	protected Number grab(Bee bee) {
		return bee.getCannotEnterHiveTicks();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.REMOVE_ALL)
			return null;
		return CollectionUtils.array(Number.class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int ticks = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (Bee bee : getEntities(event)) {
					int previous = bee.getCannotEnterHiveTicks();
					bee.setCannotEnterHiveTicks(previous + ticks);
				}
				break;
			case RESET:
			case DELETE:
				for (Bee bee : getEntities(event))
					bee.setCannotEnterHiveTicks(0);
				break;
			case REMOVE:
				for (Bee bee : getEntities(event)) {
					int previous = bee.getCannotEnterHiveTicks();
					bee.setCannotEnterHiveTicks(previous - ticks);
				}
				break;
			case REMOVE_ALL:
				break;
			case SET:
				for (Bee bee : getEntities(event))
					bee.setCannotEnterHiveTicks(ticks);
				break;
		}
	}

}
