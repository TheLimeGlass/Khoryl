package me.limeglass.khoryl.elements.block.sculkcatalyst;

import org.bukkit.block.data.type.SculkCatalyst;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("Sculk Catalyst Bloom")
@Description("Get the bloom property of a sculk catalyst.")
@Since("1.0.5")
public class SetBloom extends BlockDataSetEffect<SculkCatalyst> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetBloom.class, "[sculk [catalyst]] bloom");
	}

	@Override
	protected void execute(SculkCatalyst sculk, boolean value) {
		sculk.setBloom(value);
	}

	@Override
	protected String getPropertyName() {
		return "sculk catalyst bloom";
	}

}
