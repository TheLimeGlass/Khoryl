package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Entity Is Valid")
@Description("Returns false if the entity has died or been despawned for some other reason.")
@Since("1.0.6")
public class CondValid extends PropertyCondition<Entity> {

	static {
		register(CondValid.class, "valid", "entities");
	}

	@Override
	public boolean check(Entity entity) {
		return entity.isValid();
	}

	@Override
	protected String getPropertyName() {
		return "valid";
	}

}
