package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Bee Has Hive")
@Description("Check if the bee(s) have a hive location set.")
@Since("1.0.2")
public class CondHasHive extends EntityPropertyCondition<LivingEntity, Bee> {

	static {
		register(CondHasHive.class, PropertyType.HAVE, "[a] hive [location]", "livingentities");
	}

	@Override
	protected boolean checkEntity(Bee bee) {
		return bee.getHive() != null;
	}

	@Override
	protected String getPropertyName() {
		return "hive";
	}

}
