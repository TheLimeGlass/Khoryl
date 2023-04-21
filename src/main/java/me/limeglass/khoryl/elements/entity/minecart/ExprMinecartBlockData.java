package me.limeglass.khoryl.elements.entity.minecart;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Minecart Block Data")
@Description("The block data that is displayed inside a minecart.")
@Since("1.1.0")
public class ExprMinecartBlockData extends EntityPropertyExpression<Entity, Minecart, BlockData> {

	static {
		register(ExprMinecartBlockData.class, BlockData.class, "[minecart] block[ ]data[s]");
	}

	@Override
	@Nullable
	protected BlockData grab(Minecart entity) {
		return entity.getDisplayBlockData();
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		switch (mode) {
			case SET:
				return CollectionUtils.array(BlockData.class);
			case ADD:
			case REMOVE:
			case DELETE:
			case REMOVE_ALL:
			case RESET:
			default:
				return null;
		}
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		assert delta != null;
		BlockData blockData = (BlockData) delta[0];
		for (Minecart minecart : getEntities(event))
			minecart.setDisplayBlockData(blockData);
	}

	@Override
	protected String getPropertyName() {
		return "minecart block data";
	}

}
