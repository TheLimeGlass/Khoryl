package me.limeglass.khoryl.elements.block.sign;

import org.bukkit.block.Sign;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyCondition;

@Name("Signs - Glowing Text")
@Description("Gets whether the signs have glowing text.")
@Since("1.0.6")
public class CondGlowingText extends BlockStatePropertyCondition<Sign> {

	static {
		if (Skript.methodExists(Sign.class, "isGlowingText"))
			register(CondGlowingText.class, PropertyType.HAVE, "glowing text");
	}

	@Override
	protected boolean checkBlockState(Sign sign) {
		return sign.isGlowingText();
	}

	@Override
	protected String getPropertyName() {
		return "glowing text";
	}

}
