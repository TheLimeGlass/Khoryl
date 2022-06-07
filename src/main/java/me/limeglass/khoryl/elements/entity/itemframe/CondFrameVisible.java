package me.limeglass.khoryl.elements.entity.itemframe;

import org.bukkit.entity.ItemFrame;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Item Frame Visible")
@Description("Check if an itemframe outline is visible.")
@Since("1.0.4")
public class CondFrameVisible extends EntityPropertyCondition<ItemFrame> {

	static {
		register(CondFrameVisible.class, "[item] frame visible");
	}

	@Override
	protected boolean checkEntity(ItemFrame frame) {
		return frame.isVisible();
	}

	@Override
	protected String getPropertyName() {
		return "frame visible";
	}

}
