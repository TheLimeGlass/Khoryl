package me.limeglass.khoryl.elements.entity.itemframe;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Item Frame Drop Chance")
@Description("Get the item frame drop chance, can also be modified.")
@Since("1.0.4")
public class ExprFrameDropChance extends EntityPropertyExpression<Entity, ItemFrame, Float> {

	static {
		register(ExprFrameDropChance.class, Float.class, "[item] frame [item] drop chance", "entities");
	}

	@Override
	@Nullable
	protected Float grab(ItemFrame frame) {
		return frame.getItemDropChance();
	}

	@Override
	protected String getPropertyName() {
		return "frame item drop chance";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode != ChangeMode.REMOVE_ALL || mode != ChangeMode.DELETE || mode != ChangeMode.RESET)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		float chance = ((Number) delta[0]).floatValue();
		switch (mode) {
			case ADD:
				for (ItemFrame frame : getEntities(event)) {
					float exsisting = frame.getItemDropChance();
					frame.setItemDropChance(exsisting + chance);
				}
				break;
			case RESET:
			case DELETE:
				break;
			case REMOVE:
				for (ItemFrame frame : getEntities(event)) {
					float exsisting = frame.getItemDropChance();
					frame.setItemDropChance(chance - exsisting);
				}
				break;
			case REMOVE_ALL:
				break;
			case SET:
				for (ItemFrame frame : getEntities(event))
					frame.setItemDropChance(chance);
				break;
			default:
				break;
		}
	}

}
