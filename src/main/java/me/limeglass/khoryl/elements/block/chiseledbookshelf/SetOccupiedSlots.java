package me.limeglass.khoryl.elements.block.chiseledbookshelf;

import org.bukkit.block.data.type.ChiseledBookshelf;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("Bookshelf Occupied Slots")
@Description("Set if bookshelfs slots are occupied by books or not.")
@Since("1.1.0")
public class SetOccupiedSlots extends BlockDataSetEffect<ChiseledBookshelf> {

	static {
		if (Skript.isRunningMinecraft(new Version(1, 19)))
			register(SetOccupiedSlots.class, "bookshelf slot[s] %numbers%");
	}

	private Expression<Number> slots;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		slots = (Expression<Number>) exprs[matchedPattern ^ 0];
		return super.init(exprs, matchedPattern, isDelayed, parseResult);
	}

	@Override
	protected ChiseledBookshelf updateBlockData(ChiseledBookshelf bookshelf, boolean value) {
		slots.stream(event).map(Number::intValue).forEach(slot -> bookshelf.setSlotOccupied(slot, value));
		return bookshelf;
	}

	@Override
	protected String getPropertyName() {
		return "bookshelf slots " + slots.toString(event, printErrors);
	}

}
