package me.limeglass.khoryl.elements.block.campfire;

import org.bukkit.block.data.type.Campfire;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("Set Campfire Signal Fire")
@Description("Sets the value of the 'signal_fire' property.")
@Since("1.0.7")
public class SetSignalFire extends BlockDataSetEffect<Campfire> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(SetSignalFire.class, "signal fire [state]");
	}

	@Override
	protected Campfire updateBlockData(Campfire data, boolean value) {
		data.setSignalFire(value);
		return data;
	}

	@Override
	protected String getPropertyName() {
		return "signal fire";
	}

}
