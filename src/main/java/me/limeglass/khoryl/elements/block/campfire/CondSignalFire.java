package me.limeglass.khoryl.elements.block.campfire;

import org.bukkit.block.data.type.Campfire;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Campfire Is Signal Fire")
@Description("Checks if the campfire is in signal fire state.")
@Since("1.0.7")
public class CondSignalFire extends BlockDataPropertyCondition<Campfire> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(CondSignalFire.class, "berr(y|ies) state");
	}

	@Override
	protected boolean checkBlockData(Campfire data) {
		return data.isSignalFire();
	}

	@Override
	protected String getPropertyName() {
		return "signal fire";
	}

}
