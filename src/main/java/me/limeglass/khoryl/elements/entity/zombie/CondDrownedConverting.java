package me.limeglass.khoryl.elements.entity.zombie;

import org.bukkit.entity.Zombie;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Zombie Drowned Converting")
@Description("Checks if this entity is in the process of converting to a Drowned as a result of being underwater.")
@Since("1.0.0")
public class CondDrownedConverting extends EntityPropertyCondition<Zombie> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(CondDrownedConverting.class, PropertyType.BE, "converting to [a] drown[ed]");
	}

	@Override
	protected boolean checkEntity(Zombie zombie) {
		return zombie.isConverting();
	}

	@Override
	protected String getPropertyName() {
		return "converting to a drowned";
	}

}
