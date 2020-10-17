package me.limeglass.khoryl.elements.conditions;

import org.bukkit.entity.Entity;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Entity Is In Vehicle")
@Description("Check if the entities are in a vehicle.")
@Since("1.0.2")
public class CondIsInVehicle extends PropertyCondition<Entity> {

	static {
		register(CondIsInVehicle.class, PropertyType.BE, "in[side] [a] vehicle", "entities");
	}

	@Override
	protected String getPropertyName() {
		return "in a vehicle";
	}

	@Override
	public boolean check(Entity entity) {
		return entity.isInsideVehicle();
	}

}
