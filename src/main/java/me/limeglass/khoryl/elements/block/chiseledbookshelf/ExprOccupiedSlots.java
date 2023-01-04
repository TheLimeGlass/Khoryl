package me.limeglass.khoryl.elements.block.chiseledbookshelf;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataExpression;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Chiseled Bookshelf Occupied Slots")
@Description({
	"The slots that are occupied by books in a chiseled bookshelf.",
	"If the maxiumum of the syntax is used, it'll be the max amount of slots that can be occupied, can't be set."
})
@RequiredPlugins("Minecraft 1.19.3+")
@Since("1.0.7")
public class ExprOccupiedSlots extends BlockDataExpression<ChiseledBookshelf, Integer> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19, 3)))
			BlockDataPropertyExpression.register(ExprOccupiedSlots.class, Integer.class, "[max:max[imum] number of] occupied slots", "blocks");
	}

	private boolean max;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setBlockExpression((Expression<Block>) exprs[0]);
		max = parseResult.hasTag("max");
		return true;
	}

	@Override
	@Nullable
	protected Integer[] grab(ChiseledBookshelf bookshelf) {
		if (max)
			return CollectionUtils.array(bookshelf.getMaximumOccupiedSlots());
		return bookshelf.getOccupiedSlots().toArray(Integer[]::new);
	}

	@Override
	public boolean isSingle() {
		return max;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return max ? "maximum " : "" + "occupied slots of " + getBlockExpression().toString(event, debug);
	}

}
