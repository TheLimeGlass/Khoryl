package me.limeglass.khoryl.elements.block.sculkshrieker;

import org.bukkit.block.data.type.SculkShrieker;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Sculk Shrieker Shrieking")
@Description("Check if a schulk shrieker is shrieking.")
@Since("1.0.5")
public class CondShrieking extends BlockDataPropertyCondition<SculkShrieker> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(CondShrieking.class, PropertyType.CAN, "[sculk [shrieker]] shrieking");
	}

	@Override
	protected boolean checkBlockData(SculkShrieker shrieker) {
		return shrieker.isShrieking();
	}

	@Override
	protected String getPropertyName() {
		return "sculk shrieker shrieking";
	}

}
