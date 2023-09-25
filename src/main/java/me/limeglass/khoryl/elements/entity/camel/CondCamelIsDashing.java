package me.limeglass.khoryl.elements.entity.camel;

import org.bukkit.entity.Camel;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Camel Is Dashing")
@Description("Check if the camel(s) are dashing.")
@Since("1.1.0")
public class CondCamelIsDashing extends EntityPropertyCondition<Camel> {

	static {
		if (Skript.classExists("org.bukkit.entity.Camel"))
			register(CondCamelIsDashing.class, PropertyType.HAVE, "dashing", "livingentities");
	}

	@Override
	protected boolean checkEntity(Camel camel) {
		return camel.isDashing();
	}

	@Override
	protected String getPropertyName() {
		return "dashing";
	}

}
