package me.limeglass.khoryl.elements.block.pinkpetals;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Pink Petals Flower Amount")
@Description("The amount of flowers a pink petals patch has")
@Since("1.1.0")
@RequiredPlugins("Minecraft 1.19.4+")
public class ExprFlowerAmount extends BlockDataPropertyExpression<PinkPetals, Integer> {

	static {
		register(ExprFlowerAmount.class, Integer.class, "[:maxiumum] (flower amount|amount of flowers)");
	}

	private boolean maxiumum;

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		maxiumum = parseResult.hasTag("maxiumum");
		return super.init(exprs, matchedPattern, isDelayed, parseResult);
	}

	@Override
	protected @Nullable Integer grab(PinkPetals state) {
		if (maxiumum)
			return state.getMaximumFlowerAmount();
		return state.getFlowerAmount();
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(Number.class);
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (mode == ChangeMode.DELETE || mode == ChangeMode.RESET) {
			for (Entry<Block, PinkPetals> entry : getBlockDatasMap(event).entrySet()) {
				PinkPetals petals = entry.getValue();
				petals.setFlowerAmount(1);
				entry.getKey().setBlockData(petals);
			}
			return;
		}
		if (delta == null)
			return;
		int amount = Math.abs(((Number) delta[0]).intValue());
		switch (mode) {
			case REMOVE_ALL:
			case REMOVE:
				for (Entry<Block, PinkPetals> entry : getBlockDatasMap(event).entrySet()) {
					PinkPetals petals = entry.getValue();
					petals.setFlowerAmount(Math.max(1, petals.getFlowerAmount() - amount));
					entry.getKey().setBlockData(petals);
				}
				break;
			case ADD:
				for (Entry<Block, PinkPetals> entry : getBlockDatasMap(event).entrySet()) {
					PinkPetals petals = entry.getValue();
					petals.setFlowerAmount(petals.getFlowerAmount() + amount);
					entry.getKey().setBlockData(petals);
				}
				break;
			case SET:
				for (Entry<Block, PinkPetals> entry : getBlockDatasMap(event).entrySet()) {
					PinkPetals petals = entry.getValue();
					petals.setFlowerAmount(Math.max(1, amount));
					entry.getKey().setBlockData(petals);
				}
				break;
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

	@Override
	protected String getPropertyName() {
		return "flower amount";
	}

}
