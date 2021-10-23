package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Armour Stand Are Visible")
@Description("Check if armour stands are visible.")
@Since("1.0.4")
public class CondVisible extends EntityPropertyCondition<ArmorStand> {

	static {
		register(CondVisible.class, "armo[u]r stand visible");
	}

	@Override
	protected boolean checkEntity(ArmorStand stand) {
		return stand.isVisible();
	}

	@Override
	protected String getPropertyName() {
		return "visible";
	}

}
