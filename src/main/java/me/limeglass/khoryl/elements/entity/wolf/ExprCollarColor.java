package me.limeglass.khoryl.elements.entity.wolf;

import org.bukkit.DyeColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Wolf Collar Color")
@Description("Get the collar color of wolfs.")
@Since("1.0.0")
public class ExprCollarColor extends EntityPropertyExpression<LivingEntity, Wolf, Color> {

	static {
		register(ExprCollarColor.class, Color.class, "collar colo[u]r", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "collar colour";
	}

	@Override
	@Nullable
	protected Color grab(Wolf wolf) {
		return SkriptColor.fromDyeColor(wolf.getCollarColor()).orElse(SkriptColor.DARK_RED);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.RESET || mode == ChangeMode.SET || mode == ChangeMode.DELETE)
			return CollectionUtils.array(Color.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Color color = (Color) delta[0];
		switch (mode) {
			case DELETE:
			case RESET:
				for (Wolf wolf : getEntities(event))
					wolf.setCollarColor(DyeColor.RED);
				break;
			case SET:
				for (Wolf wolf : getEntities(event))
					wolf.setCollarColor(color.asDyeColor());
				break;
			case REMOVE_ALL:
			case REMOVE:
			case ADD:
			default:
				break;
		}
	}

}
