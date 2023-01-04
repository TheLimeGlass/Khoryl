package me.limeglass.khoryl.elements.block.cavevine;

import org.bukkit.block.data.type.CaveVines;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("Set Cave Vines Berry")
@Description("Get the berry state of a CaveVines.")
@Since("1.0.3")
public class SetBerries extends BlockDataSetEffect<CaveVines> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(SetBerries.class, "berr(y|ies) [state]");
	}

	@Override
	protected CaveVines updateBlockData(CaveVines data, boolean value) {
		data.setBerries(value);
		return data;
	}

	@Override
	protected String getPropertyName() {
		return "berries";
	}

}
