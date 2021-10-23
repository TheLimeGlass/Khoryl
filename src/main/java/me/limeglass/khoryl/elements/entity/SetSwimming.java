package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Entity Swimming")
@Description("Set the swimming state of entities.")
@Since("1.0.4")
public class SetSwimming extends EntitySetEffect<LivingEntity> {

	static {
		register(SetSwimming.class, "swim(ming)");
	}

	@Override
	public void apply(LivingEntity entity, boolean swimming) {
		entity.setSwimming(swimming);
	}

	@Override
	protected String getPropertyName() {
		return "swimming";
	}

}
