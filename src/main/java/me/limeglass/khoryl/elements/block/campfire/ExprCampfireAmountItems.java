package me.limeglass.khoryl.elements.block.campfire;

import org.bukkit.block.Block;
import org.bukkit.block.Campfire;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

@Name("Campfire Items Amount")
@Description("Returns the amount of items on the campfire.")
@Since("1.0.7")
public class ExprCampfireAmountItems extends SimpleExpression<Integer> {

	static {
		Skript.registerExpression(ExprCampfireAmountItems.class, Integer.class, ExpressionType.COMBINED, "amount of items on [the] [camp[ ]fire[s]] %blocks%");
	}

	private Expression<Block> campfires;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		campfires = (Expression<Block>) exprs[0];
		return true;
	}

	@Override
	protected @Nullable Integer[] get(Event event) {
		return campfires.stream(event)
				.filter(Campfire.class::isInstance)
				.map(Campfire.class::cast)
				.map(Campfire::getSize)
				.toArray(Integer[]::new);
	}

	@Override
	public boolean isSingle() {
		return campfires.isSingle();
	}

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "amount of items on the campfires " + campfires.toString(event, debug);
	}

}
