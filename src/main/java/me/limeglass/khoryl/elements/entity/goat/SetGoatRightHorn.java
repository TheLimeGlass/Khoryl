package me.limeglass.khoryl.elements.entity.goat;

import org.bukkit.entity.Goat;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Goat Right Horn")
@Description("Set the right horn property of a goat.")
@Since("1.0.5")
public class SetGoatRightHorn extends EntitySetEffect<Goat> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetGoatRightHorn.class, "[goat] right horn");
	}

	@Override
	public void apply(Goat goat, boolean value) {
		goat.setRightHorn(value);
	}

	@Override
	protected String getPropertyName() {
		return "right horn";
	}

}
