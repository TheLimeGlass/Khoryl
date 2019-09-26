package me.limeglass.khoryl.elements.conditions;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Leashed")
@Description("Checks whether an entity is leashed.")
@Examples("target entity is leashed")
@Since("1.0.0")
public class CondLeashed extends PropertyCondition<LivingEntity> {

	static {
		register(CondLeashed.class, PropertyType.BE, "leashed", "livingentities");
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.isLeashed();
	}

	@Override
	protected String getPropertyName() {
		return "leashed";
	}

}
