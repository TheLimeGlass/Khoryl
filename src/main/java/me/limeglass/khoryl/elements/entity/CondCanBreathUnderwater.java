package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Entity Can Breathe Underwater")
@Description("Check if entities can breathe underwater.")
@Since("1.1.0")
public class CondCanBreathUnderwater extends PropertyCondition<LivingEntity> {

	static {
		if (!Skript.methodExists(LivingEntity.class, "canBreatheUnderwater"))
			register(CondCanBreathUnderwater.class, PropertyType.CAN, "breathe under[ [the ]]water", "livingentities");
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.canBreatheUnderwater();
	}

	@Override
	protected String getPropertyName() {
		return "can breathe underwater";
	}

}
