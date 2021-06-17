package me.limeglass.khoryl.elements.block.entityblockstorage;

import org.bukkit.block.EntityBlockStorage;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyCondition;

@Name("Entity Block Storage Is Full")
@Description("Check if an entity block storage is full, such as beehives.")
@Since("1.0.3")
public class CondIsFull extends BlockStatePropertyCondition<EntityBlockStorage<?>> {

	static {
		register(CondIsFull.class, PropertyType.BE, "full", "blocks");
	}

	@Override
	protected boolean checkBlockState(EntityBlockStorage<?> storage) {
		return storage.isFull();
	}

	@Override
	protected String getPropertyName() {
		return "is full";
	}

}
