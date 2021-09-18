package me.limeglass.khoryl.elements.block.jukeboxes;

import org.bukkit.Material;
import org.bukkit.block.Jukebox;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Jukeboxes - Playing")
@Description("Get the record that is currently playing in a jukebox.")
@Since("1.0.4")
public class ExprPlaying extends BlockStatePropertyExpression<Jukebox, ItemType> {

	static {
		register(ExprPlaying.class, ItemType.class, "record [item] playing [inside]");
	}

	@Override
	@Nullable
	protected ItemType grab(Jukebox jukebox) {
		return new ItemType(jukebox.getPlaying());
	}

	@Override
	protected String getPropertyName() {
		return "record playing";
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		Material record = ((ItemType) delta[0]).getMaterial();
		if (!record.isRecord())
		for (Jukebox jukebox : getBlockStates(event)) {
			if (mode == ChangeMode.RESET) {
				jukebox.eject();
				continue;
			}
			jukebox.setPlaying(record);
			jukebox.update();
		}
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.RESET)
			return CollectionUtils.array(ItemType.class);
		return null;
	}

}
