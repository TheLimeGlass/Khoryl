package me.limeglass.khoryl.elements.entity.minecart;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Minecart Max Speed")
@Description("How fast the max minecraft speeds will be.")
@Since("1.1.0")
public class ExprMinecartSpeed extends EntityPropertyExpression<Entity, Minecart, Double> {

	static {
		register(ExprMinecartSpeed.class, Double.class, "minecart max speed");
	}

	@Override
	@Nullable
	protected Double grab(Minecart entity) {
		return entity.getMaxSpeed();
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		switch (mode) {
			case ADD:
			case REMOVE:
			case SET:
				return CollectionUtils.array(Number.class);
			case DELETE:
			case REMOVE_ALL:
			case RESET:
			default:
				return null;
		}
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		double speed = ((Number) delta[0]).doubleValue();
		switch (mode) {
			case ADD:
				for (Minecart minecart : getEntities(event)) {
					double existing = minecart.getMaxSpeed();
					minecart.setMaxSpeed(existing + speed);
				}
				break;
			case REMOVE:
				for (Minecart minecart : getEntities(event)) {
					double existing = minecart.getMaxSpeed();
					minecart.setMaxSpeed(existing - speed);
				}
				break;
			case SET:
				for (Minecart minecart : getEntities(event))
					minecart.setMaxSpeed(speed);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

	@Override
	protected String getPropertyName() {
		return "minecart max speed";
	}

}
