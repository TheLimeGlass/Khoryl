package me.limeglass.khoryl.elements.entity.minecart;

import org.bukkit.entity.Minecart;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Minecart Is Slow When Empty")
@Description("Check if a minecart will be slow when it's empty.")
@Since("1.1.0")
public class CondIsSlowWhenEmpty extends EntityPropertyCondition<Minecart> {

	static {
		register(CondIsSlowWhenEmpty.class, "slow when empty");
	}

	@Override
	protected boolean checkEntity(Minecart minecart) {
		return minecart.isSlowWhenEmpty();
	}

	@Override
	protected String getPropertyName() {
		return "slow when empty";
	}

}
