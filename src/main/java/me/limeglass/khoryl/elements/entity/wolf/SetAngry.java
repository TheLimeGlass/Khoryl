package me.limeglass.khoryl.elements.entity.wolf;

import org.bukkit.entity.Wolf;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Wolf Angry")
@Description("Get if a wolfie is angry.")
@Since("1.0.0")
public class SetAngry extends EntitySetEffect<Wolf> {

	static {
		register(SetAngry.class, "angry", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "angry";
	}

	@Override
	public void apply(Wolf wolf, boolean angry) {
		wolf.setAngry(angry);
	}

}
