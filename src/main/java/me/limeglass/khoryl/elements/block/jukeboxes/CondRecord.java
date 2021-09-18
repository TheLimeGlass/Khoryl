package me.limeglass.khoryl.elements.block.jukeboxes;

import org.bukkit.block.data.type.Jukebox;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockDataPropertyCondition;

@Name("Jukeboxes - Has Record")
@Description("Checks if a jukebox has a record inside currently.")
@Since("1.0.4")
public class CondRecord extends BlockDataPropertyCondition<Jukebox> {

	static {
		register(CondRecord.class, PropertyType.HAVE, "record [inside]", "blocks");
	}

	@Override
	protected boolean checkBlockData(Jukebox jukebox) {
		return jukebox.hasRecord();
	}

	@Override
	protected String getPropertyName() {
		return "record";
	}

}
