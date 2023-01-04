package me.limeglass.khoryl.elements.block.chiseledbookshelf;

import java.util.Arrays;

import org.bukkit.block.Block;
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

@Name("End Portal Frame Set Eye")
@Description("Set if an ender portal frame has an eye of ender in it.")
@Since("1.0.6")
public class SetOccupiedSlots extends BlockDataSetEffect<ChiseledBookshelf> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetOccupiedSlots.class, "slot[s] %numbers%");
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (exprs.length != 2)
			Skript.error("There was not two expressions in the SetEffect class " + getClass().getName() + " exprs: " + Arrays.toString(exprs));
		make = matchedPattern == 1;
		setExpression((Expression<Block>) exprs[0]);
		setValueExpression((Expression<Boolean>) exprs[1]);
		return true;
	}

	@Override
	protected ChiseledBookshelf updateBlockData(ChiseledBookshelf bookshelf, boolean value) {
		bookshelf.setSlotOccupied(0, value);
		return bookshelf;
	}

	@Override
	protected String getPropertyName() {
		return "has eye of ender";
	}

}
