package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Collidable")
@Description("Set the collidable state of entities.")
@Since("1.0.6")
public class SetCollidable extends EntitySetEffect<LivingEntity> {

	static {
		register(SetCollidable.class, "collidable [state]");
	}

	@Override
	public void apply(LivingEntity entity, boolean collidable) {
		entity.setCollidable(collidable);
	}

	@Override
	protected String getPropertyName() {
		return "collidable";
	}

}
