package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Armour Stand Has Arms")
@Description("Check if armour stands have arms.")
@Since("1.0.4")
public class CondArms extends EntityPropertyCondition<ArmorStand> {

	static {
		register(CondArms.class, PropertyType.HAVE, "[armo[u]r stand] arms");
	}

	@Override
	protected boolean checkEntity(ArmorStand stand) {
		return stand.hasArms();
	}

	@Override
	protected String getPropertyName() {
		return "arms";
	}

}
