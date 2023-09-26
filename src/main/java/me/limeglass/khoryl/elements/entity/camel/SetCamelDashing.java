package me.limeglass.khoryl.elements.entity.camel;

import org.bukkit.entity.Camel;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Camel Dashing")
@Description("Set if the camel(s) are dashing.")
@Since("1.1.0")
public class SetCamelDashing extends EntitySetEffect<Camel> {

	static {
		if (Skript.classExists("org.bukkit.entity.Camel"))
			register(SetCamelDashing.class, "dashing");
	}

	@Override
	public void apply(Camel camel, boolean dashing) {
		camel.setDashing(dashing);
	}

	@Override
	protected String getPropertyName() {
		return "dashing";
	}

}
