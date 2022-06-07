package me.limeglass.khoryl.elements.entity.steerable;

import org.bukkit.entity.Steerable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Set Steerable Saddle")
@Description("Set if a steerable entity like a pig or strider have a saddle.")
@Since("1.0.4")
public class SetCharging extends EntitySetEffect<Steerable> {

	static {
		register(SetCharging.class, "[has [a]] [steerable] saddle");
	}

	@Override
	public void apply(Steerable strider, boolean saddle) {
		strider.setSaddle(saddle);
	}

	@Override
	protected String getPropertyName() {
		return "saddle";
	}

}
