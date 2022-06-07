package me.limeglass.khoryl.elements.block.sculkshrieker;

import org.bukkit.block.data.type.SculkShrieker;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("Sculk Shrieker Shrieking")
@Description("Get the shrieking property of a sculk shrieker.")
@Since("1.0.5")
public class SetShrieking extends BlockDataSetEffect<SculkShrieker> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetShrieking.class, "[sculk [shrieker]] shriek[ing]");
	}

	@Override
	protected void execute(SculkShrieker sculk, boolean value) {
		sculk.setShrieking(value);
	}

	@Override
	protected String getPropertyName() {
		return "sculk shrieker shrieking";
	}

}
