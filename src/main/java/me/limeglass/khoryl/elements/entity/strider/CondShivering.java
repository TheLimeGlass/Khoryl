package me.limeglass.khoryl.elements.entity.strider;

import org.bukkit.entity.Strider;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Strider Shivering")
@Description("Check if a strider is shivering.")
@Since("1.0.4")
public class CondShivering extends EntityPropertyCondition<Strider> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 16)))
			register(CondShivering.class, "[strider] shivering");
	}

	@Override
	protected boolean checkEntity(Strider strider) {
		return strider.isShivering();
	}

	@Override
	protected String getPropertyName() {
		return "shivering";
	}

}
