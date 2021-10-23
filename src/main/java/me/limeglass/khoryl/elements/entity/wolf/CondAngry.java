package me.limeglass.khoryl.elements.entity.wolf;

import org.bukkit.entity.Wolf;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Wolf is Angry")
@Description("Checks if a wolfie is angry.")
@Since("1.0.0")
public class CondAngry extends EntityPropertyCondition<Wolf> {

	static {
		register(CondAngry.class, PropertyType.BE, "[a[n]] angry [(wolf|dog)]");
	}

	@Override
	protected boolean checkEntity(Wolf wolf) {
		return wolf.isAngry();
	}

	@Override
	protected String getPropertyName() {
		return "an angry wolf";
	}

}
