package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Entity Is Inside Vehicle")
@Description("Check if entities are inside a vehicle.")
@Since("1.0.4")
public class CondInsideVehicle extends PropertyCondition<Entity> {

	static {
		register(CondInsideVehicle.class, "inside [a] vehicle", "entities");
	}

	@Override
	public boolean check(Entity entity) {
		return entity.isInsideVehicle();
	}

	@Override
	protected String getPropertyName() {
		return "inside vehicle";
	}

}
