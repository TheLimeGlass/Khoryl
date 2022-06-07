package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;

@Name("Visual Fire")
@Description("Check if entities are displaying that they're on fire.")
@Since("1.0.4")
public class CondVisualFire extends PropertyCondition<Entity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(CondVisualFire.class, "(vi(ual|sibly [on])|on) fire", "entities");
	}

	@Override
	public boolean check(Entity entity) {
		return entity.isVisualFire();
	}

	@Override
	protected String getPropertyName() {
		return "visibly on fire";
	}

}
