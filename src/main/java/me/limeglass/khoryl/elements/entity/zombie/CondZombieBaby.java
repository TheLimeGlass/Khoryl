package me.limeglass.khoryl.elements.entity.zombie;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Zombie Baby")
@Description("Checks whether the zombie is a baby.")
@Since("1.0.0")
public class CondZombieBaby extends EntityPropertyCondition<LivingEntity, Zombie> {

	static {
		register(CondZombieBaby.class, PropertyType.BE, "zombie baby", "livingentities");
	}

	@Override
	protected boolean checkEntity(Zombie zombie) {
		return !zombie.isAdult();
	}

	@Override
	protected String getPropertyName() {
		return "zombie baby";
	}

}
