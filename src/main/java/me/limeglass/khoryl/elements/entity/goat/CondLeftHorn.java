package me.limeglass.khoryl.elements.entity.goat;

import org.bukkit.entity.Goat;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Goat Has Left Horn")
@Description("Check if the goat(s) have a left horn.")
@Since("1.0.5")
public class CondLeftHorn extends EntityPropertyCondition<Goat> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(CondLeftHorn.class, "left horn");
	}

	@Override
	protected boolean checkEntity(Goat goat) {
		return goat.hasLeftHorn();
	}

	@Override
	protected String getPropertyName() {
		return "left horn";
	}

}
