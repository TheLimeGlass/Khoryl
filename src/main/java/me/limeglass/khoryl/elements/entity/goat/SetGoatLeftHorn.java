package me.limeglass.khoryl.elements.entity.goat;

import org.bukkit.entity.Goat;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Goat Left Horn")
@Description("Set the left horn property of a goat.")
@Since("1.0.5")
public class SetGoatLeftHorn extends EntitySetEffect<Goat> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetGoatLeftHorn.class, "[goat] left horn");
	}

	@Override
	public void apply(Goat goat, boolean value) {
		goat.setLeftHorn(value);
	}

	@Override
	protected String getPropertyName() {
		return "left horn";
	}

}
