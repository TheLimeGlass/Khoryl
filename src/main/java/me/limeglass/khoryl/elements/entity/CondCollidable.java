package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Collidable")
@Description("Check if living entities have their collidable state enabled.")
@Since("1.0.6")
public class CondCollidable extends PropertyCondition<LivingEntity> {

	static {
		if (Skript.methodExists(LivingEntity.class, "isCollidable"))
			register(CondCollidable.class, "collidable", "livingentities");
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.isCollidable();
	}

	@Override
	protected String getPropertyName() {
		return "collidable";
	}

}
