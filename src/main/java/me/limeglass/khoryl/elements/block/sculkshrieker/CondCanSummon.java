package me.limeglass.khoryl.elements.block.sculkshrieker;

import org.bukkit.block.data.type.SculkShrieker;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Sculk Shrieker Can Summon")
@Description("Check if a schulk shrieker can summon.")
@Since("1.0.5")
public class CondCanSummon extends BlockDataPropertyCondition<SculkShrieker> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(CondCanSummon.class, PropertyType.CAN, "summon [warden]");
	}

	@Override
	protected boolean checkBlockData(SculkShrieker shrieker) {
		return shrieker.isCanSummon();
	}

	@Override
	protected String getPropertyName() {
		return "sculk catalyst bloom";
	}

}
