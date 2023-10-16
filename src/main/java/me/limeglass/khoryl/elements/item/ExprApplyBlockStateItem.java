package me.limeglass.khoryl.elements.item;

import java.util.Objects;

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
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

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
@Since("1.1.1")
public class ExprApplyBlockStateItem extends SimpleExpression<ItemStack> {

	static {
		Skript.registerExpression(ExprApplyBlockStateItem.class, ItemStack.class, ExpressionType.COMBINED,
				"%itemtype/itemstack% with [the] block state [meta] [(from|of) %block%]",
				"%itemtype/itemstack% with %block%'[s] block state [meta]");
	}

	private Expression<Block> blockState;
	private Expression<?> items;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		blockState = (Expression<Block>) exprs[1];
		items = exprs[0];
		return true;
	}

	@Override
	protected @Nullable ItemStack[] get(Event event) {
		Object object = blockState.getSingle(event);
		if (object == null)
			return new ItemStack[0];
		Block block = (Block) object;
		BlockState state = block.getState();
		return items.stream(event)
				.map(item -> {
					if (item instanceof ItemType)
						return ((ItemType) item).getRandom();
					return ((ItemStack) item);
				})
				.map(itemStack -> {
					ItemMeta meta = itemStack.getItemMeta();
					if (!(meta instanceof BlockStateMeta))
						return new ItemStack[0];
					BlockStateMeta blockStateMeta = ((BlockStateMeta) meta);
					blockStateMeta.setBlockState(state);
					itemStack.setItemMeta(blockStateMeta);
					return itemStack;
				})
				.filter(Objects::nonNull)
				.toArray(ItemStack[]::new);
	}

	@Override
	public boolean isSingle() {
		return items.isSingle();
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return items.toString(event, debug) + " with block state " + blockState.toString(event, debug);
	}

}
