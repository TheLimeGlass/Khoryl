package me.limeglass.khoryl.elements.block.repeater;

import org.bukkit.block.data.type.Repeater;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Minimum Repeater Delay")
@Description("Get minimum delay of a repeater. Redstone.")
@Since("1.0.4")
public class ExprMinimumDelay extends BlockDataPropertyExpression<Repeater, Integer> {

	static {
		register(ExprMinimumDelay.class, Integer.class, "minimum [repeater] delay", "blocks");
	}

	@Override
	@Nullable
	protected Integer grab(Repeater repeater) {
		return repeater.getMinimumDelay();
	}

	@Override
	protected String getPropertyName() {
		return "minimum repeater delay";
	}

}
