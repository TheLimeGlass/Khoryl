package me.limeglass.khoryl.elements.entity.armorstand;

import org.bukkit.entity.ArmorStand;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Armour Stand Visible")
@Description("Set if the armour stands are visible.")
@Since("1.0.4")
public class SetVisible extends EntitySetEffect<ArmorStand> {

	static {
		register(SetVisible.class, "armo[u]r stand visible");
	}

	@Override
	public void apply(ArmorStand stand, boolean visible) {
		stand.setVisible(visible);
	}

	@Override
	protected String getPropertyName() {
		return "visible";
	}

}
