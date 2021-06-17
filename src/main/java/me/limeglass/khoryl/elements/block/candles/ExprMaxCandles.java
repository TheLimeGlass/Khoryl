package me.limeglass.khoryl.elements.block.candles;

import org.bukkit.block.data.type.Candle;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Max Candle Count")
@Description("Get the max candle count of a candle.")
@Since("1.0.3")
public class ExprMaxCandles extends BlockDataPropertyExpression<Candle, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprMaxCandles.class, Number.class, "max candle count", "blocks");
	}

	@Override
	@Nullable
	protected Number grab(Candle candle) {
		return candle.getMaximumCandles();
	}

	@Override
	protected String getPropertyName() {
		return "max candle count";
	}

}
