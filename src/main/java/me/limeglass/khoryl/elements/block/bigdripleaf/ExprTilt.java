package me.limeglass.khoryl.elements.block.bigdripleaf;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.block.data.type.BigDripleaf.Tilt;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Big Dripleaf Tilt")
@Description("Get the tilt of a Big Dripleaf.")
@Since("1.0.3")
public class ExprTilt extends BlockDataPropertyExpression<BigDripleaf, Tilt> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprTilt.class, Tilt.class, "[big dripleaf] tilt", "blocks");
	}

	@Override
	@Nullable
	protected Tilt grab(BigDripleaf leaf) {
		return leaf.getTilt();
	}

	@Override
	protected String getPropertyName() {
		return "tilt";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Tilt.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Tilt tilt = (Tilt) delta[0];
		for (Entry<Block, BigDripleaf> entry : getBlockDatasMap(event).entrySet())
			entry.getValue().setTilt(tilt);
	}

}