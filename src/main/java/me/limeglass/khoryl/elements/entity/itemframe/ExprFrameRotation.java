package me.limeglass.khoryl.elements.entity.itemframe;

import org.bukkit.Rotation;
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

@Name("Item Frame Rotation")
@Description("Get the rotation of an item frame.")
@Since("1.0.4")
public class ExprFrameRotation extends EntityPropertyExpression<Entity, ItemFrame, Rotation> {

	static {
		register(ExprFrameRotation.class, Rotation.class, "[item] frame rotation", "entities");
	}

	@Override
	protected @Nullable Rotation grab(ItemFrame frame) {
		return frame.getRotation();
	}

	@Override
	protected String getPropertyName() {
		return "item frame rotation";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Rotation.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Rotation rotation = (Rotation) delta[0];
		for (ItemFrame frame : getEntities(event))
			frame.setRotation(rotation);
	}

}
