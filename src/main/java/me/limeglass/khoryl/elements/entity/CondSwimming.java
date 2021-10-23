package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;

@Name("Entity Swimming")
@Description("Check if entities are swimming.")
@Since("1.0.4")
public class CondSwimming extends PropertyCondition<LivingEntity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(CondSwimming.class, "swimming", "livingentities");
	}

	@Override
	public boolean check(LivingEntity entity) {
		return entity.isSwimming();
	}

	@Override
	protected String getPropertyName() {
		return "swimming";
	}

}
