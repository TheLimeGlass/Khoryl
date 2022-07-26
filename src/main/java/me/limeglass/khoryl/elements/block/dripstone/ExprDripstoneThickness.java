package me.limeglass.khoryl.elements.block.dripstone;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.block.data.type.PointedDripstone.Thickness;
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

@Name("Dripstone Thickness")
@Description("Get the thickness of a dripstone.")
@Since("1.0.3")
public class ExprDripstoneThickness extends BlockDataPropertyExpression<PointedDripstone, Thickness> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprDripstoneThickness.class, Thickness.class, "[dripstone] thickness", "blocks");
	}

	@Override
	@Nullable
	protected Thickness grab(PointedDripstone dripstone) {
		return dripstone.getThickness();
	}

	@Override
	protected String getPropertyName() {
		return "dripstone thickness";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Thickness thickness = (Thickness) delta[0];
		for (Entry<Block, PointedDripstone> entry : getBlockDatasMap(event).entrySet()) {
			PointedDripstone dripstone = entry.getValue();
			dripstone.setThickness(thickness);
			entry.getKey().setBlockData(dripstone);
		}
	}

}
