package me.limeglass.khoryl.elements.block.dropper;

import org.bukkit.block.Dropper;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStateEffect;

@Name("Dropper Drop")
@Description("Tries to drop a randomly selected item from the dropper's inventory, following the normal behavior of a dropper..")
@Since("1.0.6")
public class EffDropperDrop extends BlockStateEffect<Dropper> {

	static {
		Skript.registerEffect(EffDropperDrop.class, "trigger drop (for|on) [dropper[s]] %blocks%");
	}

	@Override
	protected void execute(Dropper dropper) {
		dropper.drop();
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug)
			return "drop";
		return "drop " + getExpression().toString(event, debug);
	}

}
