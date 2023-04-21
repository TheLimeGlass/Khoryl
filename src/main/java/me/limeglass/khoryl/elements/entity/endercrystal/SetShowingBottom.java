package me.limeglass.khoryl.elements.entity.endercrystal;

import org.bukkit.entity.EnderCrystal;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Ender Crystal Showing Bottom")
@Description("Set if ender crystals have a base showing.")
@Since("1.1.0")
public class SetShowingBottom extends EntitySetEffect<EnderCrystal> {

	static {
		register(SetShowingBottom.class, "showing bottom [base]");
	}

	@Override
	public void apply(EnderCrystal crystal, boolean bottom) {
		crystal.setShowingBottom(bottom);
	}

	@Override
	protected String getPropertyName() {
		return "showing bottom base";
	}

}
