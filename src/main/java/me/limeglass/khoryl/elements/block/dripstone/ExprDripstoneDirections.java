package me.limeglass.khoryl.elements.block.dripstone;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Direction;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataExpression;

@Name("Dripstone Directions")
@Description("Get the directions of a dripstone.")
@Since("1.0.3")
public class ExprDripstoneDirections extends BlockDataExpression<PointedDripstone, Direction> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			PropertyExpression.register(ExprDripstoneDirections.class, Direction.class, "dripstone [vertical] direction[s]", "blocks");
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
	protected Direction[] grab(PointedDripstone dripstone) {
		return dripstone.getVerticalDirections().stream().map(blockface -> new Direction(blockface, 1)).toArray(Direction[]::new);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Direction.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Direction direction = (Direction) delta[0];
		for (Entry<Block, PointedDripstone> entry : getBlockDatasMap(event).entrySet())
			entry.getValue().setVerticalDirection(toBlockFace(direction.getDirection(entry.getKey())));
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "dripstone vertical directions " + getBlockExpression();
	}

	private static BlockFace toBlockFace(Vector direction) {
		BlockFace r = null;
		double d = Double.MAX_VALUE;
		for (BlockFace f : BlockFace.values()) {
			final double a = Math.pow(f.getModX() - direction.getX(), 2) + Math.pow(f.getModY() - direction.getY(), 2) + Math.pow(f.getModZ() - direction.getZ(), 2);
			if (a < d) {
				d = a;
				r = f;
			}
		}
		assert r != null;
		return r;
	}

}
