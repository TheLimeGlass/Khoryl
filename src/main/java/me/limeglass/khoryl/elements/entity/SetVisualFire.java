package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Visual Fire")
@Description("Set the visual fire state of entities.")
@Since("1.0.4")
public class SetVisualFire extends EntitySetEffect<Entity> {

	static {
		register(SetVisualFire.class, "[is] (vi(ual|sibly [on])|on) fire");
	}

	@Override
	public void apply(Entity entity, boolean fire) {
		entity.setVisualFire(fire);
	}

	@Override
	protected String getPropertyName() {
		return "visual fire";
	}

}
