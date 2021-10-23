package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Armour Stand Are Small")
@Description("Check if armour stands are small.")
@Since("1.0.4")
public class CondSmall extends EntityPropertyCondition<ArmorStand> {

	static {
		register(CondSmall.class, "[armo[u]r stand] small");
	}

	@Override
	protected boolean checkEntity(ArmorStand stand) {
		return stand.isSmall();
	}

	@Override
	protected String getPropertyName() {
		return "small";
	}

}
