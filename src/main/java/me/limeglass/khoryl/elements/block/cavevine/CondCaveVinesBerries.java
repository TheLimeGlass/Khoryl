package me.limeglass.khoryl.elements.block.cavevine;

import org.bukkit.block.data.type.CaveVines;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Cave Vines Berry")
@Description("Check if the berry state of a CaveVines exists.")
@Since("1.0.3")
public class CondCaveVinesBerries extends BlockDataPropertyCondition<CaveVines> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(CondCaveVinesBerries.class, "berr(y|ies) state");
	}

	@Override
	protected boolean checkBlockData(CaveVines data) {
		return data.isBerries();
	}

	@Override
	protected String getPropertyName() {
		return "berries";
	}

}
