package me.limeglass.khoryl.elements.block.repeater;

import org.bukkit.block.data.type.Repeater;
import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.SettableBlockDataPropertyCondition;

@Name("Repeater Lock")
@Description("Get the lock state of a repeater. Redstone.")
@Since("1.0.4")
public class ExprRepeaterLocked extends SettableBlockDataPropertyCondition<Repeater> {

	static {
		register(ExprRepeaterLocked.class, "[repeater] locked", "repeater lock", EffRepeaterLocked.class);
	}

	@Override
	protected boolean checkBlockData(Repeater repeater) {
		return repeater.isLocked();
	}

	@Override
	protected String getPropertyName() {
		return "repeater lock";
	}

	private class EffRepeaterLocked extends PropertyEffect {
		@Override
		protected void execute(Event event) {
			for (Repeater repeater : getArray(getExpression(), event))
				repeater.setLocked(getBoolean(event));
		}
	}

}
