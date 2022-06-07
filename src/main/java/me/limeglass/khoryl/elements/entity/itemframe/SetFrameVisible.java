package me.limeglass.khoryl.elements.entity.itemframe;

import org.bukkit.entity.ItemFrame;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Item Frame Visible")
@Description("Get the item frame visible state.")
@Since("1.0.4")
public class SetFrameVisible extends EntitySetEffect<ItemFrame> {

	static {
		register(SetFrameVisible.class, "[item] frame visible");
	}

	@Override
	public void apply(ItemFrame frame, boolean visible) {
		frame.setVisible(visible);
	}

	@Override
	protected String getPropertyName() {
		return "frame visible";
	}

}
