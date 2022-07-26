package me.limeglass.khoryl.elements.block.cavevine;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.CaveVines;
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

@Name("Cave Vines Berry")
@Description("Get the berry state of a CaveVines.")
@Since("1.0.3")
public class ExprBerry extends BlockDataPropertyExpression<CaveVines, Boolean> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprBerry.class, Boolean.class, "berr(y|ies) state", "blocks");
	}

	@Override
	@Nullable
	protected Boolean grab(CaveVines plant) {
		return plant.isBerries();
	}

	@Override
	protected String getPropertyName() {
		return "berry state";
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
		Boolean berries = (Boolean) delta[0];
		for (Entry<Block, CaveVines> entry : getBlockDatasMap(event).entrySet()) {
			CaveVines vines = entry.getValue();
			vines.setBerries(berries);
			entry.getKey().setBlockData(vines);
		}
	}

}