package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Armour Stand Base Plate")
@Description("Set if the armour stands should have base plates.")
@Since("1.0.4")
public class SetBasePlate extends EntitySetEffect<ArmorStand> {

	static {
		register(SetBasePlate.class, "[have [a]] [armo[u]r stand] base plate");
	}

	@Override
	public void apply(ArmorStand stand, boolean plate) {
		stand.setBasePlate(plate);
	}

	@Override
	protected String getPropertyName() {
		return "base plate";
	}

}
