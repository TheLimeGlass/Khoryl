package me.limeglass.khoryl.elements.block.lidded;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lidded;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.elements.entity.merchant.EffMerchantRecipe;

@Name("Open/Close Lidded")
@Description("Set to open or close a lidded block. (Chest, Barrel, Shulker, Etc)")
@Since("1.0.3")
public class EffOpenCloseLidded extends Effect {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 16)))
			Skript.registerEffect(EffMerchantRecipe.class, "(1¦open|2¦close) [lidded] blocks %blocks%");
	}

	private Expression<Block> blocks;
	private boolean open;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		blocks = (Expression<Block>) exprs[0];
		open = parseResult.mark == 1;
		return true;
	}

	@Override
	protected void execute(Event event) {
		for (Block block : blocks.getArray(event)) {
			BlockState state = block.getState();
			if (!(state instanceof Lidded))
				continue;
			Lidded lidded = (Lidded) state;
			if (open)
				lidded.open();
			else
				lidded.close();
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null)
			return open ? "open " : "close " + " lidded blocks";
		return open ? "open " : "close " + " lidded blocks " + blocks.toString(event, debug);
	}

}
