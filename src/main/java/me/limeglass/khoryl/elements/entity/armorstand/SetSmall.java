package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Armour Stand Small")
@Description("Set if the armour stands are small.")
@Since("1.0.4")
public class SetSmall extends EntitySetEffect<ArmorStand> {

	static {
		register(SetSmall.class, "small");
	}

	@Override
	public void apply(ArmorStand stand, boolean small) {
		stand.setSmall(small);
	}

	@Override
	protected String getPropertyName() {
		return "small";
	}

}
