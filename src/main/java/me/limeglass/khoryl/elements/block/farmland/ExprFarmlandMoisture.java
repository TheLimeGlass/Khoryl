package me.limeglass.khoryl.elements.block.farmland;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Farmland Moisture")
@Description("Get the moisture of a farmland block.")
@Since("1.0.7")
public class ExprFarmlandMoisture extends BlockDataPropertyExpression<Farmland, Integer> {

	static {
		if (Skript.classExists("org.bukkit.block.data.type.Farmland") && Skript.methodExists(Farmland.class, "getMaximumMoisture"))
			register(ExprFarmlandMoisture.class, Integer.class, "[max:max[imum]] moisture", "blocks");
	}

	private boolean max;

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		max = parseResult.hasTag("max");
		return super.init(exprs, matchedPattern, isDelayed, parseResult);
	}

	@Override
	@Nullable
	protected Integer grab(Farmland farmland) {
		if (max)
			return farmland.getMaximumMoisture();
		return farmland.getMoisture();
	}

	@Override
	protected String getPropertyName() {
		return "moisture";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.REMOVE_ALL || max)
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
				for (Entry<Block, Farmland> entry : getBlockDatasMap(event).entrySet()) {
					Farmland farmland = entry.getValue();
					int existing = farmland.getMoisture();
					farmland.setMoisture(existing + count);
					entry.getKey().setBlockData(farmland);
				}
				break;
			case REMOVE:
				for (Entry<Block, Farmland> entry : getBlockDatasMap(event).entrySet()) {
					Farmland farmland = entry.getValue();
					int existing = farmland.getMoisture();
					farmland.setMoisture(existing - count);
					entry.getKey().setBlockData(farmland);
				}
				break;
			case DELETE:
			case RESET:
				for (Entry<Block, Farmland> entry : getBlockDatasMap(event).entrySet()) {
					Farmland farmland = entry.getValue();
					farmland.setMoisture(0);
					entry.getKey().setBlockData(farmland);
				}
				break;
			case SET:
				for (Entry<Block, Farmland> entry : getBlockDatasMap(event).entrySet()) {
					Farmland farmland = entry.getValue();
					farmland.setMoisture(count);
					entry.getKey().setBlockData(farmland);
				}
				break;
			default:
				break;
		}
	}

}
