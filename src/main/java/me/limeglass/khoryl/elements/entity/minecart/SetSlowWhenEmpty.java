package me.limeglass.khoryl.elements.entity.minecart;

import org.bukkit.entity.Strider;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Minecart Slow When Empty")
@Description("Set if striders are to be shivering.")
@Since("1.0.4")
public class SetSlowWhenEmpty extends EntitySetEffect<Strider> {

	static {
		register(SetSlowWhenEmpty.class, "[strider] shivering");
	}

	@Override
	public void apply(Strider strider, boolean shivering) {
		strider.setShivering(shivering);
	}

	@Override
	protected String getPropertyName() {
		return "strider shivering";
	}

}
