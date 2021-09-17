package me.limeglass.khoryl.elements.block.repeater;

import org.bukkit.block.data.type.Repeater;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Repeater Lock")
@Description("Get the lock state of a repeater. Redstone.")
@Since("1.0.4")
public class CondRepeaterLocked extends BlockDataPropertyCondition<Repeater> {

	static {
		register(CondRepeaterLocked.class, "[repeater] locked");
	}

	@Override
	protected boolean checkBlockData(Repeater repeater) {
		return repeater.isLocked();
	}

	@Override
	protected String getPropertyName() {
		return "repeater lock";
	}

}
