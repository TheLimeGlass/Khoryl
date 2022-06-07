package me.limeglass.khoryl.elements.entity.chestedhorse;

import org.bukkit.entity.ChestedHorse;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Chested Horse Carrying")
@Description("Check if a horse is carrying a chest.")
@Since("1.0.4")
public class CondCarryingChest extends EntityPropertyCondition<ChestedHorse> {

	static {
		if (Skript.classExists("org.bukkit.entity.ChestedHorse"))
			register(CondCarryingChest.class, "carrying chest");
	}

	@Override
	protected boolean checkEntity(ChestedHorse horse) {
		return horse.isCarryingChest();
	}

	@Override
	protected String getPropertyName() {
		return "carrying chest";
	}

}
