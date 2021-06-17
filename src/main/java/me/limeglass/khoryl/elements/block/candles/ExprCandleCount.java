package me.limeglass.khoryl.elements.block.candles;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.Candle;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Candle Count")
@Description("Get the candle count of a candle.")
@Since("1.0.3")
public class ExprCandleCount extends BlockDataPropertyExpression<Candle, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprCandleCount.class, Number.class, "candle count", "blocks");
	}

	@Override
	@Nullable
	protected Number grab(Candle candle) {
		return candle.getCandles();
	}

	@Override
	protected String getPropertyName() {
		return "candle count";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.REMOVE_ALL)
			return null;
		return CollectionUtils.array(Number.class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int count = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (Entry<Block, Candle> entry : getBlockDatasMap(event).entrySet()) {
					Candle candle = entry.getValue();
					int existing = candle.getCandles();
					candle.setCandles(existing + count);
				}
				break;
			case REMOVE:
				for (Entry<Block, Candle> entry : getBlockDatasMap(event).entrySet()) {
					Candle candle = entry.getValue();
					int existing = candle.getCandles();
					candle.setCandles(existing - count);
				}
				break;
			case DELETE:
			case RESET:
				for (Entry<Block, Candle> entry : getBlockDatasMap(event).entrySet())
					entry.getValue().setCandles(0);
				break;
			case SET:
				for (Entry<Block, Candle> entry : getBlockDatasMap(event).entrySet())
					entry.getValue().setCandles(count);
				break;
			default:
				break;
		}
	}

}
