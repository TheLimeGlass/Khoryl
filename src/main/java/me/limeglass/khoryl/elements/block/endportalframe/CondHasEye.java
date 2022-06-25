package me.limeglass.khoryl.elements.block.endportalframe;

import org.bukkit.block.data.type.EndPortalFrame;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("End Portal Frame Has Eye")
@Description("Returns if the ender portal frame has an eye of ender in it.")
@Since("1.0.6")
public class CondHasEye extends BlockDataPropertyCondition<EndPortalFrame> {

	static {
		register(CondHasEye.class, "has eye [of ender]");
	}

	@Override
	protected boolean checkBlockData(EndPortalFrame frame) {
		return frame.hasEye();
	}

	@Override
	protected String getPropertyName() {
		return "has eye of ender";
	}

}
