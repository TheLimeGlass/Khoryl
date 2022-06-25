package me.limeglass.khoryl.elements.block.sign;

import org.bukkit.block.Sign;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyCondition;

@Name("Signs - Editable")
@Description("Marks whether signs can be edited by players.")
@Since("1.0.6")
public class CondSignEditable extends BlockStatePropertyCondition<Sign> {

	static {
		if (Skript.methodExists(Sign.class, "isEditable"))
			register(CondSignEditable.class, "editable");
	}

	@Override
	protected boolean checkBlockState(Sign sign) {
		return sign.isEditable();
	}

	@Override
	protected String getPropertyName() {
		return "editable";
	}

}
