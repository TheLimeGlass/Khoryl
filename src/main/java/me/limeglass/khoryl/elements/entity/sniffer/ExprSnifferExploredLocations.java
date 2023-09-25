package me.limeglass.khoryl.elements.entity.sniffer;

import java.util.stream.Stream;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Sniffer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.EntityExpression;

@Name("Sniffer Explored Locations")
@Description("Get all the explored locations of the sniffer(s).")
@Since("1.1.0")
public class ExprSnifferExploredLocations extends EntityExpression<LivingEntity, Sniffer, Location> {

	static {
		SimplePropertyExpression.register(ExprSnifferExploredLocations.class, Location.class, "explored locations", "livingentities");
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setEntitiesExpression((Expression<LivingEntity>) exprs[0]);
		return true;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "explored locations of " + getEntitiesExpression().toString(event, debug);
	}

	@Override
	protected @Nullable Stream<Location> get(Event event, Sniffer entity) {
		return getEntities(event).stream().flatMap(sniffer -> sniffer.getExploredLocations().stream());
	}

}
