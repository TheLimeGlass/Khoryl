package me.limeglass.khoryl.elements.entity.vex;

import org.bukkit.entity.Vex;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Vex Charging")
@Description("Set if vexes are to be charging.")
@Since("1.0.4")
public class SetCharging extends EntitySetEffect<Vex> {

	static {
		register(SetCharging.class, "vex charg(e|ing)");
	}

	@Override
	public void apply(Vex vex, boolean charging) {
		vex.setCharging(charging);
	}

	@Override
	protected String getPropertyName() {
		return "vex charging";
	}

}
