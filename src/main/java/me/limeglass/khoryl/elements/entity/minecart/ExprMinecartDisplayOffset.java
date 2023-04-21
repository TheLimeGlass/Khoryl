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

@Name("Minecart Display Offset")
@Description({
	"How much to offset the minecart block display by.",
	"A value of 16 moves the block up by exactly one multiple of its height.",
	"Positive values move the block upwards, while negative values move it downwards."
})
@Since("1.1.0")
public class ExprMinecartDisplayOffset extends EntityPropertyExpression<Entity, Minecart, Integer> {

	static {
		register(ExprMinecartDisplayOffset.class, Integer.class, "minecart display [block [data]] offset");
	}

	@Override
	@Nullable
	protected Integer grab(Minecart entity) {
		return entity.getDisplayBlockOffset();
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
		int offset = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (Minecart minecart : getEntities(event)) {
					int existing = minecart.getDisplayBlockOffset();
					minecart.setDisplayBlockOffset(existing + offset);
				}
				break;
			case REMOVE:
				for (Minecart minecart : getEntities(event)) {
					int existing = minecart.getDisplayBlockOffset();
					minecart.setDisplayBlockOffset(existing - offset);
				}
				break;
			case SET:
				for (Minecart minecart : getEntities(event))
					minecart.setDisplayBlockOffset(offset);
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
		return "minecart display offset";
	}

}
