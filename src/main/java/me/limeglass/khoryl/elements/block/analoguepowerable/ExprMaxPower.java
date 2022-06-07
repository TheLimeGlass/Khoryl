package me.limeglass.khoryl.elements.block.analoguepowerable;

import org.bukkit.block.data.AnaloguePowerable;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Analogue Powerable Maximum Power")
@Description("Get the maximum power state for analogue powerable blocks.")
@Since("1.0.5")
public class ExprMaxPower extends BlockDataPropertyExpression<AnaloguePowerable, Integer> {

	static {
		if (Skript.methodExists(AnaloguePowerable.class, "getMaximumPower"))
			register(ExprMaxPower.class, Integer.class, "max[imium] [analogue] [redstone] [signal] power", "blocks");
	}

	@Override
	@Nullable
	protected Integer grab(AnaloguePowerable powerable) {
		return powerable.getMaximumPower();
	}

	@Override
	protected String getPropertyName() {
		return "max redstone signal power";
	}

}
