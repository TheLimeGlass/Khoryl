package me.limeglass.khoryl.elements.entity.minecart;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Minecart Flying Velocity")
@Description("The velocity at which the minecart's flying velocity will be.")
@Since("1.1.0")
public class ExprMinecartFlyingVector extends EntityPropertyExpression<Entity, Minecart, Vector> {

	static {
		register(ExprMinecartFlyingVector.class, Vector.class, "[minecart] flying ve(ctor|locity) [mod]");
	}

	@Override
	@Nullable
	protected Vector grab(Minecart entity) {
		return entity.getFlyingVelocityMod();
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		switch (mode) {
			case SET:
				return CollectionUtils.array(Vector.class);
			case ADD:
			case REMOVE:
			case DELETE:
			case REMOVE_ALL:
			case RESET:
			default:
				return null;
		}
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		assert delta != null;
		Vector vector = (Vector) delta[0];
		for (Minecart minecart : getEntities(event))
			minecart.setFlyingVelocityMod(vector);
	}

	@Override
	protected String getPropertyName() {
		return "minecart flying vector";
	}

}
