package me.limeglass.khoryl.elements.entity.minecart;

import org.bukkit.entity.Minecart;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Minecart Slow When Empty")
@Description("Set if minecarts are to be slow when they're empty.")
@Since("1.0.4")
public class SetSlowWhenEmpty extends EntitySetEffect<Minecart> {

	static {
		register(SetSlowWhenEmpty.class, "slow when empty state");
	}

	@Override
	public void apply(Minecart minecart, boolean slow) {
		minecart.setSlowWhenEmpty(slow);
	}

	@Override
	protected String getPropertyName() {
		return "slow when empty state";
	}

}
