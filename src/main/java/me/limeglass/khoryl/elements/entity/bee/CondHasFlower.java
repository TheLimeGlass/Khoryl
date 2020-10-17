package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Bee Has Flower")
@Description("Check if the bee(s) have a flower location set.")
@Since("1.0.2")
public class CondHasFlower extends EntityPropertyCondition<LivingEntity, Bee> {

	static {
		register(CondHasFlower.class, PropertyType.HAVE, "[a] flower [location]", "livingentities");
	}

	@Override
	protected boolean checkEntity(Bee bee) {
		return bee.getFlower() != null;
	}

	@Override
	protected String getPropertyName() {
		return "flower";
	}

}
