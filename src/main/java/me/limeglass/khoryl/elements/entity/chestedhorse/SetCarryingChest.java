package me.limeglass.khoryl.elements.entity.chestedhorse;

import org.bukkit.entity.ChestedHorse;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Horse Chest")
@Description("Set if a horse is carrying a chest.")
@Since("1.0.4")
public class SetCarryingChest extends EntitySetEffect<ChestedHorse> {

	static {
		if (Skript.classExists("org.bukkit.entity.ChestedHorse"))
			register(SetCarryingChest.class, "carrying chest");
	}

	@Override
	public void apply(ChestedHorse horse, boolean chest) {
		horse.setCarryingChest(chest);
	}

	@Override
	protected String getPropertyName() {
		return "carrying chest";
	}

}
