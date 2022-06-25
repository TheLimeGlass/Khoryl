package me.limeglass.khoryl.elements.block.dispenser;

import org.bukkit.block.Dispenser;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStateEffect;

@Name("Dispenser Dispense")
@Description("Attempts to dispense the contents of the dispensers.")
@Since("1.0.6")
public class EffDispense extends BlockStateEffect<Dispenser> {

	static {
		Skript.registerEffect(EffDispense.class, "dispense %blocks%");
	}

	@Override
	protected void execute(Dispenser dispenser) {
		dispenser.dispense();
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug)
			return "dispense";
		return "dispense " + getExpression().toString(event, debug);
	}

}
