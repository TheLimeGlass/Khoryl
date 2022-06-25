package me.limeglass.khoryl.elements.block.endportalframe;

import org.bukkit.block.data.type.EndPortalFrame;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("End Portal Frame Set Eye")
@Description("Set if an ender portal frame has an eye of ender in it.")
@Since("1.0.6")
public class SetHasEye extends BlockDataSetEffect<EndPortalFrame> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetHasEye.class, "[ha(s|ve)] [an] eye [of ender]");
	}

	@Override
	protected void execute(EndPortalFrame frame, boolean value) {
		frame.setEye(value);
	}

	@Override
	protected String getPropertyName() {
		return "has eye of ender";
	}

}
