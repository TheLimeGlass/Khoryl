package me.limeglass.khoryl.elements.entity.steerable;

import org.bukkit.entity.Steerable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Steerable Saddle")
@Description("Check if a steerable entity like a pig of strider have a saddle.")
@Since("1.0.4")
public class CondShivering extends EntityPropertyCondition<Steerable> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 16)))
			register(CondShivering.class, PropertyType.HAVE, "[a] [steerable] saddle");
	}

	@Override
	protected boolean checkEntity(Steerable steerable) {
		return steerable.hasSaddle();
	}

	@Override
	protected String getPropertyName() {
		return "saddle";
	}

}
