package me.limeglass.khoryl.elements.block.campfire;

import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStateExpression;

@Name("Campfire Cook Time")
@Description("Collect the total or current cooking time for a slot on the campfires. Not including the word 'total' is the current cooking time.")
@Since("1.0.7")
public class ExprCampfireCookTime extends BlockStateExpression<Campfire, Timespan> {

	static {
		SimplePropertyExpression.register(ExprCampfireCookTime.class, Timespan.class, "[camp[ ]fire] [:total] cook[ing] time (of|(i|o)n) [slot] %number%", "blocks");
	}

	private Expression<Number> slot;
	private boolean total;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setBlockExpression((Expression<Block>) exprs[matchedPattern ^ 1]);
		slot = (Expression<Number>) exprs[matchedPattern];
		total = parseResult.hasTag("total");
		return true;
	}

	@Override
	@Nullable
	protected Timespan grab(Campfire state) {
		Number slot = this.slot.getSingle(event);
		if (slot == null)
			return null;
		if (total)
			return Timespan.fromTicks_i(state.getCookTimeTotal(slot.intValue()));
		return Timespan.fromTicks_i(state.getCookTime(slot.intValue()));
	}

	@Override
	public String toString(Event event, boolean debug) {
		return (total ? "total " : "") + "cook time on slot " + slot.toString(event, false);
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.REMOVE_ALL || mode == ChangeMode.RESET || mode == ChangeMode.DELETE)
			return null;
		return CollectionUtils.array(Timespan.class, Number.class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (mode != ChangeMode.RESET && mode != ChangeMode.DELETE && delta == null)
			return;

		Number number = this.slot.getSingle(event);
		if (number == null)
			return;
		int slot = number.intValue();
		int ticks = delta != null ? delta[0] instanceof Number ? (Integer) delta[0]:(int) ((Timespan) delta[0]).getTicks_i() : 0;
		switch (mode) {
			case RESET:
			case DELETE:
			case SET:
				for (Campfire campfire : getBlockStates(event)) {
					if (total) {
						campfire.setCookTimeTotal(slot, ticks);
					} else {
						campfire.setCookTime(slot, ticks);
					}
					campfire.update(true);
				}
				break;
			case REMOVE:
				for (Campfire campfire : getBlockStates(event)) {
					if (total) {
						campfire.setCookTimeTotal(slot, Math.max(campfire.getCookTimeTotal(slot) - ticks, 0));
					} else {
						campfire.setCookTime(slot, Math.max(campfire.getCookTime(slot) - ticks, 0));
					}
					campfire.update(true);
				}
				break;
			case ADD:
				for (Campfire campfire : getBlockStates(event)) {
					if (total) {
						campfire.setCookTimeTotal(slot, campfire.getCookTimeTotal(slot) + ticks);
					} else {
						campfire.setCookTime(slot, campfire.getCookTime(slot) + ticks);
					}
					campfire.update(true);
				}
				break;
			case REMOVE_ALL:
			default:
				break;
		}
	}

}
