package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Bee Has Nectar")
@Description("Check if the bee(s) have nectar.")
@Since("1.0.2")
public class CondHasNectar extends EntityPropertyCondition<Bee> {

	static {
		register(CondHasNectar.class, PropertyType.HAVE, "nectar", "livingentities");
	}

	@Override
	protected boolean checkEntity(Bee bee) {
		return bee.hasNectar();
	}

	@Override
	protected String getPropertyName() {
		return "nectar";
	}

}
