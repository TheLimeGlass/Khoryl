package me.limeglass.khoryl.elements.entity.mob;

import org.bukkit.entity.Mob;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Mob Awareness")
@Description("whether this mob is aware of it's surroundings.")
@Since("1.0.7")
public class CondAware extends EntityPropertyCondition<Mob> {

	static {
		Skript.registerCondition(CondAware.class, "%livingentities% (is|are) aware", "%livingentities% (isn't|is not|aren't|are not) aware",
				"%livingentities% (has|have) awareness", "%livingentities% (doesn't|does not|do not|don't) have aware");
	}

	@Override
	protected boolean negated(boolean provided) {
		return getMatchedPattern() == 1 || getMatchedPattern() == 3;
	}

	@Override
	protected boolean checkEntity(Mob mob) {
		return mob.isAware();
	}

	@Override
	protected String getPropertyName() {
		return "awareness";
	}

}
