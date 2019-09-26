package me.limeglass.khoryl.elements.entity.zombie.villager;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.ZombieVillager;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Zombie Villager Profession")
@Description("Gets the profession of zombie villagers.")
@Since("1.0.0")
/**
 * Skript "should" have proper support for getting this but at the moment it doesn't.
 * So the syntax will include 'zombie villager' as this may be supported in future Skript versions.
 * There is currently proper setting of professions using entity data, and that should be used over a changer.
 * Khoryl will also register the classinfo if Skript doesn't.
 */
public class ExprZombieProfession extends EntityPropertyExpression<LivingEntity, ZombieVillager, Profession> {

	static {
		register(ExprZombieProfession.class, Profession.class, "zombie villager profession", "livingentities");
	}

	@Override
	@Nullable
	protected Profession grab(ZombieVillager villager) {
		return villager.getVillagerProfession();
	}

	@Override
	protected String getPropertyName() {
		return "zombie villager profession";
	}

}
