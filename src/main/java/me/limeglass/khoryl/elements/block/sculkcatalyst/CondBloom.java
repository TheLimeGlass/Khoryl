package me.limeglass.khoryl.elements.block.sculkcatalyst;

import org.bukkit.block.data.type.SculkCatalyst;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Sculk Catalyst Bloom")
@Description("Check if a schulk catalyst is in bloom.")
@Since("1.0.5")
public class CondBloom extends BlockDataPropertyCondition<SculkCatalyst> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(CondBloom.class, "[in] [sculk [catalyst]] bloom");
	}

	@Override
	protected boolean checkBlockData(SculkCatalyst sculk) {
		return sculk.isBloom();
	}

	@Override
	protected String getPropertyName() {
		return "sculk catalyst bloom";
	}

}
