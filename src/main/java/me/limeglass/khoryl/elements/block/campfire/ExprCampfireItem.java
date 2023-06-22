package me.limeglass.khoryl.elements.block.campfire;

import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStateExpression;

@Name("Campfire Item")
@Description("Collect the item on a slot of the campfires.")
@Since("1.0.7")
public class ExprCampfireItem extends BlockStateExpression<Campfire, ItemStack> {

	static {
		SimplePropertyExpression.register(ExprCampfireItem.class, ItemStack.class, "[camp[ ]fire] item (of|(i|o)n) [slot] %number%", "blocks");
	}

	private Expression<Number> slot;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setBlockExpression((Expression<Block>) exprs[matchedPattern ^ 1]);
		slot = (Expression<Number>) exprs[matchedPattern];
		return true;
	}

	@Override
	@Nullable
	protected ItemStack grab(Campfire state) {
		Number slot = this.slot.getSingle(event);
		if (slot == null)
			return null;
		return state.getItem(slot.intValue());
	}

	@Override
	public String toString(Event event, boolean debug) {
		return "camp fire on slot " + slot.toString(event, false);
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.RESET || mode == ChangeMode.DELETE)
			return CollectionUtils.array(ItemStack.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (mode != ChangeMode.RESET && mode != ChangeMode.DELETE && delta == null)
			return;
		Number number = this.slot.getSingle(event);
		if (number == null)
			return;
		int slot = number.intValue();
		switch (mode) {
			case RESET:
			case DELETE:
				for (Campfire campfire : getBlockStates(event)) {
					campfire.setItem(slot, null);
					campfire.update(true);
				}
				break;
			case SET:
				if (delta[0] == null)
					return;
				ItemStack item = (ItemStack) delta[0];
				for (Campfire campfire : getBlockStates(event)) {
					campfire.setItem(slot, item);
					campfire.update(true);
				}
				break;
			default:
				break;
		}
	}

}
