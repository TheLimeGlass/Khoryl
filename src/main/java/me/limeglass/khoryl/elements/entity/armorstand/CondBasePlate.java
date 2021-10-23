package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Armour Stand Has Base Plate")
@Description("Check if armour stands have base plates.")
@Since("1.0.4")
public class CondBasePlate extends EntityPropertyCondition<ArmorStand> {

	static {
		register(CondBasePlate.class, PropertyType.HAVE, "[armo[u]r stand] base plate");
	}

	@Override
	protected boolean checkEntity(ArmorStand stand) {
		return stand.hasBasePlate();
	}

	@Override
	protected String getPropertyName() {
		return "base plate";
	}

}
