package me.limeglass.khoryl.elements.entity.goat;

import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Goat Is Screaming")
@Description("Check if the goat(s) are screaming.")
@Since("1.0.3")
public class CondIsScreaming extends EntityPropertyCondition<LivingEntity, Goat> {

	static {
		register(CondIsScreaming.class, "screaming", "livingentities");
	}

	@Override
	protected boolean checkEntity(Goat goat) {
		return goat.isScreaming();
	}

	@Override
	protected String getPropertyName() {
		return "screaming";
	}

}
