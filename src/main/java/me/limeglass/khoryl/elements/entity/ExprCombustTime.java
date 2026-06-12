package me.limeglass.khoryl.elements.entity;

import ch.njol.skript.util.Timespan.TimePeriod;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.jetbrains.annotations.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

@Name("Combuster")
@Description("Returns the combuster of the event, either a block or an entity.")
@Examples("if type of combuster was lava:")
@Since("1.0.6")
public class ExprCombustTime extends SimpleExpression<Timespan> {

	static {
		Skript.registerExpression(ExprCombustTime.class, Timespan.class, ExpressionType.SIMPLE, "[the] combust (duration|time)");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		if (!getParser().isCurrentEvent(EntityCombustEvent.class, EntityCombustByEntityEvent.class, EntityCombustByBlockEvent.class)) {
			Skript.error("The combust duration is only usable in a combust event.", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "combust duration";
	}

	@Override
	protected @Nullable Timespan[] get(Event event) {
		return CollectionUtils.array(new Timespan((long) (((EntityCombustByBlockEvent)event).getDuration() * 1000)));
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.SET)
			return CollectionUtils.array(Timespan.class);
		return null;
	}

	@Override
	public void change(Event e, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		float seconds = ((Timespan) delta[0]).getAs(TimePeriod.SECOND);
		EntityCombustEvent event = (EntityCombustEvent) e;
		switch (mode) {
			case ADD:
				float add = event.getDuration();
				event.setDuration(add + seconds);
				break;
			case REMOVE:
				float remove = event.getDuration();
				event.setDuration(remove - seconds);
				break;
			case SET:
				event.setDuration(seconds);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

}
