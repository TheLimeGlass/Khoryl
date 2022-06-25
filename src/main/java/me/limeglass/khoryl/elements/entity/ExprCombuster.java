package me.limeglass.khoryl.elements.entity;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

@Name("Combuster")
@Description("Returns the combuster of the event, either a block or an entity.")
@Examples("if type of combuster was lava:")
@Since("1.0.6")
public class ExprCombuster extends SimpleExpression<Object> {

	static {
		Skript.registerExpression(ExprCombuster.class, Object.class, ExpressionType.SIMPLE, "[the] combuster");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Object> getReturnType() {
		return Object.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(EntityCombustByEntityEvent.class, EntityCombustByBlockEvent.class)) {
			Skript.error("The combuster is only usable in a combust event.", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "combuster";
	}

	@Override
	protected @Nullable Object[] get(Event event) {
		if (event instanceof EntityCombustByEntityEvent)
			return CollectionUtils.array(((EntityCombustByEntityEvent)event).getCombuster());
		if (event instanceof EntityCombustByEntityEvent)
			return CollectionUtils.array(((EntityCombustByBlockEvent)event).getCombuster());
		return null;
	}

}
