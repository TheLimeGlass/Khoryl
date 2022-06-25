package me.limeglass.khoryl.elements.block.sign;

import org.bukkit.block.Sign;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStateSetEffect;

@Name("Signs - Glowing Text")
@Description("Sets whether the signs have glowing text.")
@Since("1.0.6")
public class SetGlowingText extends BlockStateSetEffect<Sign> {

	static {
		if (Skript.methodExists(Sign.class, "setGlowingText", boolean.class))
			register(SetGlowingText.class, "[sign] glowing text");
	}

	@Override
	protected void execute(Sign sign, boolean value) {
		sign.setGlowingText(value);
	}

	@Override
	protected String getPropertyName() {
		return "glowing text";
	}

}
