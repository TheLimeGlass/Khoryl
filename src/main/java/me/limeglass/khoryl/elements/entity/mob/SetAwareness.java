package me.limeglass.khoryl.elements.entity.mob;

import org.bukkit.entity.Mob;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Mob Awareness")
@Description("Set whether this mob is aware of its surroundings.")
@Since("1.0.7")
public class SetAwareness extends EntitySetEffect<Mob> {

	static {
		register(SetAwareness.class, "aware[ness]", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "aware";
	}

	@Override
	public void apply(Mob mob, boolean value) {
		mob.setAware(value);
	}

}
