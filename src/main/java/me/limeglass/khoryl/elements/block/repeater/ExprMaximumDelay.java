package me.limeglass.khoryl.elements.block.repeater;

import org.bukkit.block.data.type.Repeater;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Maximum Repeater Delay")
@Description("Get maximum delay of a repeater. Redstone.")
@Since("1.0.4")
public class ExprMaximumDelay extends BlockDataPropertyExpression<Repeater, Integer> {

	static {
		register(ExprMaximumDelay.class, Integer.class, "maximum [repeater] delay");
	}

	@Override
	@Nullable
	protected Integer grab(Repeater repeater) {
		return repeater.getMaximumDelay();
	}

	@Override
	protected String getPropertyName() {
		return "maximum repeater delay";
	}

}
