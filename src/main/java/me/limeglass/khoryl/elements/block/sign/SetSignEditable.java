package me.limeglass.khoryl.elements.block.sign;

import org.bukkit.block.Sign;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStateSetEffect;

@Name("Signs - Editable")
@Description("Sets whether the signs can be edited by players.")
@Since("1.0.6")
public class SetSignEditable extends BlockStateSetEffect<Sign> {

	static {
		if (Skript.methodExists(Sign.class, "setEditable", boolean.class))
			register(SetSignEditable.class, "[sign] editable [state]");
	}

	@Override
	protected void execute(Sign sign, boolean value) {
		sign.setEditable(value);
	}

	@Override
	protected String getPropertyName() {
		return "editable";
	}

}
