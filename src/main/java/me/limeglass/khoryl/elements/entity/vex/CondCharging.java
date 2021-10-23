package me.limeglass.khoryl.elements.entity.vex;

import org.bukkit.entity.Vex;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Vex Charging")
@Description("Check if a vex is charging.")
@Since("1.0.4")
public class CondCharging extends EntityPropertyCondition<Vex> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(CondCharging.class, "vex charg(ing|e)");
	}

	@Override
	protected boolean checkEntity(Vex vex) {
		return vex.isCharging();
	}

	@Override
	protected String getPropertyName() {
		return "charging";
	}

}
