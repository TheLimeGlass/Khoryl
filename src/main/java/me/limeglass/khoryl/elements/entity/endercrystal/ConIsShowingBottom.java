package me.limeglass.khoryl.elements.entity.endercrystal;

import org.bukkit.entity.EnderCrystal;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Ender Crystal Is Showing Bottom")
@Description("Check if ender crystals are showing the bottom frame.")
@Since("1.1.0")
public class ConIsShowingBottom extends EntityPropertyCondition<EnderCrystal> {

	static {
		register(ConIsShowingBottom.class, "showing bottom [base]");
	}

	@Override
	protected boolean checkEntity(EnderCrystal crystal) {
		return crystal.isShowingBottom();
	}

	@Override
	protected String getPropertyName() {
		return "showing bottom";
	}

}
