package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Bee Has Stung")
@Description("Check if the bee(s) have stung before.")
@Since("1.0.2")
public class CondHasStung extends EntityPropertyCondition<Bee> {

	static {
		register(CondHasStung.class, PropertyType.HAVE, "stung");
	}

	@Override
	protected boolean checkEntity(Bee bee) {
		return bee.hasStung();
	}

	@Override
	protected String getPropertyName() {
		return "stung";
	}

}
