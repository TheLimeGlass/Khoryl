package me.limeglass.khoryl.elements.conditions;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;

@Name("Riptiding")
@Description("Checks to see if an entity is currently using the Riptide enchantment.")
@Examples("target entity is riptiding")
@Since("1.0.0")
public class CondRiptiding extends PropertyCondition<LivingEntity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(CondRiptiding.class, PropertyType.BE, "riptiding", "livingentities");
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.isRiptiding();
	}

	@Override
	protected String getPropertyName() {
		return "riptiding";
	}

}
