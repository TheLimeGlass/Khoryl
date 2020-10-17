package me.limeglass.khoryl.elements.conditions;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;

@Name("Living Entity Can Pickup Items")
@Description("Check if the livingentities can pickup items.")
@Since("1.0.2")
public class CondCanPickupItems extends PropertyCondition<LivingEntity> {

	static {
		register(CondCanPickupItems.class, PropertyType.CAN, "pickup items", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "pickup items";
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.getCanPickupItems();
	}

}
