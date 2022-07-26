package me.limeglass.khoryl.elements.block.directional;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
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

@Name("Block Directional")
@Description("Get block direction of a Directional block.")
@Since("1.0.3")
public class ExprDirectional extends BlockDataPropertyExpression<Directional, Direction> {

	static {
		register(ExprDirectional.class, Direction.class, "block direction", "blocks");
	}

	@Override
	@Nullable
	protected Direction grab(Directional directional) {
		return new Direction(directional.getFacing(), 1);
	}

	@Override
	protected String getPropertyName() {
		return "block direction";
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
		for (Entry<Block, Directional> entry : getBlockDatasMap(event).entrySet()) {
			Directional directional = entry.getValue();
			Block block = entry.getKey();
			directional.setFacing(toBlockFace(direction.getDirection(block)));
			block.setBlockData(directional);
		}
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
