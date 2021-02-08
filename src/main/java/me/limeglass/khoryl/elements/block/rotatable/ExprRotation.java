package me.limeglass.khoryl.elements.block.rotatable;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rotatable;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Direction;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Block Rotation")
@Description("Get block rotation of a Rotatable block.")
@Since("1.0.3")
public class ExprRotation extends BlockDataPropertyExpression<Rotatable, Direction> {

	static {
		register(ExprRotation.class, Direction.class, "block rotation", "blocks");
	}

	@Override
	@Nullable
	protected Direction grab(Rotatable rotatable) {
		return new Direction(rotatable.getRotation(), 1);
	}

	@Override
	protected String getPropertyName() {
		return "block rotation";
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
		for (Entry<Block, Rotatable> entry : getBlockDatasMap(event).entrySet())
			entry.getValue().setRotation(toBlockFace(direction.getDirection(entry.getKey())));
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
