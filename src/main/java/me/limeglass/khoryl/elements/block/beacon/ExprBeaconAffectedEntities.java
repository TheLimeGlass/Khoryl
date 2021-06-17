package me.limeglass.khoryl.elements.block.beacon;

import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.BlockStateExpression;

@Name("Beacon Affected Enitites")
@Description("Get all the enities in range of a beacon.")
@Since("1.0.3")
public class ExprBeaconAffectedEntities extends BlockStateExpression<Beacon, LivingEntity> {

	static {
		PropertyExpression.register(ExprBeaconAffectedEntities.class, LivingEntity.class, "(affected entities around|entities in range of) beacon[s] ", "blocks");
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setBlockExpression((Expression<Block>) exprs[0]);
		return true;
	}

	@Override
	@Nullable
	protected LivingEntity[] grab(Beacon beacon) {
		return beacon.getEntitiesInRange().stream().toArray(LivingEntity[]::new);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "affected entities around beacon " + getBlockExpression().toString(event, debug);
	}

}
