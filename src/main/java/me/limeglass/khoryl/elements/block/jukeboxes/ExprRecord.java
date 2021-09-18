package me.limeglass.khoryl.elements.block.jukeboxes;

import org.bukkit.block.Jukebox;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Jukeboxes - Record")
@Description("Gets the current actual item inside the jukebox.")
@Since("1.0.4")
public class ExprRecord extends BlockStatePropertyExpression<Jukebox, ItemStack> {

	static {
		register(ExprRecord.class, ItemStack.class, "record [item]");
	}

	@Override
	@Nullable
	protected ItemStack grab(Jukebox jukebox) {
		return jukebox.getRecord();
	}

	@Override
	protected String getPropertyName() {
		return "record item";
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		ItemStack record = (ItemStack) delta[0];
		for (Jukebox jukebox : getBlockStates(event)) {
			if (mode == ChangeMode.RESET) {
				jukebox.eject();
				continue;
			}
			jukebox.setRecord(record);
			jukebox.update();
		}
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.RESET)
			return CollectionUtils.array(ItemStack.class);
		return null;
	}

}
