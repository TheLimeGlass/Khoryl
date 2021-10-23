package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Armour Stand Marker")
@Description("Set it the armour stands should have a marker. A marker means the armour stands have a small hitbox.")
@Since("1.0.4")
public class SetMarker extends EntitySetEffect<ArmorStand> {

	static {
		register(SetMarker.class, "[a[n]] [armo[u]r stand] marker");
	}

	@Override
	public void apply(ArmorStand stand, boolean marker) {
		stand.setMarker(marker);
	}

	@Override
	protected String getPropertyName() {
		return "marker";
	}

}
