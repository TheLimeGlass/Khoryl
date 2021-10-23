package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Armour Stand Has Marker")
@Description("Check if armour stands have a marker. A marker means it has a small hitbox.")
@Since("1.0.4")
public class CondMarker extends EntityPropertyCondition<ArmorStand> {

	static {
		register(CondMarker.class, "[armo[u]r stand] marker");
	}

	@Override
	protected boolean checkEntity(ArmorStand stand) {
		return stand.isMarker();
	}

	@Override
	protected String getPropertyName() {
		return "marker";
	}

}
