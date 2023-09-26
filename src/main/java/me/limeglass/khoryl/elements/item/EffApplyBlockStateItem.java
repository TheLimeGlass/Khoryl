package me.limeglass.khoryl.elements.item;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.ItemMetaSyntax;

@Name("Apply BlockState To Item")
@Description({
	"Applies the block state of a block to an item.",
	"This is how you apply a banner pattern to a shield. See examples"
})
@Examples({
	"on right click on any standing banner:",
		"\tplayer's tool is a shield",
		"\tapply block state from clicked block to player's tool"
})
@Since("1.1.0")
public class EffApplyBlockStateItem extends Effect implements ItemMetaSyntax<BlockStateMeta> {

	static {
		Skript.registerEffect(EffApplyBlockStateItem.class, "apply [the] block state [meta] [(from|of) %block%] to %itemtype/itemstack%");
	}

	private Expression<Block> blockState;
	private Expression<?> item;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		blockState = (Expression<Block>) exprs[0];
		item = exprs[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		Object object = blockState.getSingle(event);
		if (object == null)
			return;
		Block block = (Block) object;
		BlockState state = block.getState();
		Object item = this.item.getSingle(event);
		if (item == null)
			return;
		if (item instanceof ItemType) {
			ItemType itemType = ((ItemType) item);
			ItemMeta meta = itemType.getItemMeta();
			if (!(meta instanceof BlockStateMeta))
				return;
			BlockStateMeta blockStateMeta = ((BlockStateMeta) meta);
			blockStateMeta.setBlockState(state);
			itemType.setItemMeta(blockStateMeta);
		} else if (item instanceof ItemStack) {
			ItemStack itemStack = ((ItemStack) item);
			ItemMeta meta = itemStack.getItemMeta();
			if (!(meta instanceof BlockStateMeta))
				return;
			BlockStateMeta blockStateMeta = ((BlockStateMeta) meta);
			blockStateMeta.setBlockState(state);
			itemStack.setItemMeta(blockStateMeta);
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "apply block state " + blockState.toString(event, debug) + " to " + item.toString(event, debug);
	}

}
