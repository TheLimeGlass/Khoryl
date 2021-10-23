package me.limeglass.khoryl.elements.entity.zombie.villager;

import org.bukkit.entity.ZombieVillager;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Zombie Villager Converting")
@Description("Checks if this entity is in the process of converting to a Villager as a result of being cured.")
@Since("1.0.0")
public class CondVillagerConverting extends EntityPropertyCondition<ZombieVillager> {

	static {
		register(CondVillagerConverting.class, PropertyType.BE, "converting [to a villager]");
	}

	@Override
	protected boolean checkEntity(ZombieVillager villager) {
		return villager.isConverting();
	}

	@Override
	protected String getPropertyName() {
		return "converting to a villager";
	}

}
