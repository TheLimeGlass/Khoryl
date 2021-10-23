package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;

@Name("Entity Climbing")
@Description("Check if entities are climbing.")
@Since("1.0.4")
public class CondClimbing extends PropertyCondition<LivingEntity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(CondClimbing.class, "climbing", "livingentities");
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.isClimbing();
	}

	@Override
	protected String getPropertyName() {
		return "climbing";
	}

}
