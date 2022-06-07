package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;

@Name("Entity Is Frozen")
@Description("Check if entities are frozen.")
@Since("1.0.4")
public class CondFrozen extends PropertyCondition<Entity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(CondFrozen.class, "frozen", "entities");
	}

	@Override
	public boolean check(Entity entity) {
		return entity.isFrozen();
	}

	@Override
	protected String getPropertyName() {
		return "frozen";
	}

}
