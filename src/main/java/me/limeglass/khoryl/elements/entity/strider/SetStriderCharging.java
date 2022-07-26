package me.limeglass.khoryl.elements.entity.strider;

import org.bukkit.entity.Strider;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Strider Shivering")
@Description("Set if striders are to be shivering.")
@Since("1.0.4")
public class SetStriderCharging extends EntitySetEffect<Strider> {

	static {
		register(SetStriderCharging.class, "[strider] shivering");
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
