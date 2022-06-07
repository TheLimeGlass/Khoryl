package me.limeglass.khoryl.elements.entity.itemframe;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Item Frame Item")
@Description("Get the item of an item frame.")
@Since("1.0.4")
public class ExprFrameItem extends EntityPropertyExpression<Entity, ItemFrame, ItemStack> {

	static {
		register(ExprFrameItem.class, ItemStack.class, "[item] frame item", "entities");
	}

	@Override
	protected @Nullable ItemStack grab(ItemFrame frame) {
		return frame.getItem();
	}

	@Override
	protected String getPropertyName() {
		return "frame item";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(ItemStack.class, ItemType.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		ItemStack item = delta[0] instanceof ItemStack ? (ItemStack) delta[0] : ((ItemType) delta[0]).getRandom();
		for (ItemFrame frame : getEntities(event))
			frame.setItem(item);
	}

}
