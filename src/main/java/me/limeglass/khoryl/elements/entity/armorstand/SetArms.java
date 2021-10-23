package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Armour Stand Arms")
@Description("Set if the armour stands should have arms.")
@Since("1.0.4")
public class SetArms extends EntitySetEffect<ArmorStand> {

	static {
		register(SetArms.class, "[have] [armo[u]r stand] arms");
	}

	@Override
	public void apply(ArmorStand stand, boolean arms) {
		stand.setArms(arms);
	}

	@Override
	protected String getPropertyName() {
		return "arms";
	}

}
