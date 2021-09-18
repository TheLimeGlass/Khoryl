package me.limeglass.khoryl.elements.block.entityblockstorage;

import org.bukkit.block.EntityBlockStorage;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Beacon Secondary Effect")
@Description("Get the secondary potion effect of a beacon.")
@Since("1.0.3")
public class ExprStorageMaxEntities extends BlockStatePropertyExpression<EntityBlockStorage<?>, Number> {

	static {
		register(ExprStorageMaxEntities.class, Number.class, "max[imum] entit(ies|y count)");
	}

	@Override
	@Nullable
	protected Number grab(EntityBlockStorage<?> storage) {
		return storage.getMaxEntities();
	}

	@Override
	protected String getPropertyName() {
		return "maximum entity count";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null || delta[0] == null)
			return;
		int max = ((Number) delta[0]).intValue();
		for (EntityBlockStorage<?> storage : getBlockStates(event)) {
			storage.setMaxEntities(max);
			storage.update();
		}
	}

}
