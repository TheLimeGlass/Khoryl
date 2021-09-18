package me.limeglass.khoryl.elements.block.jukeboxes;

import org.bukkit.block.Jukebox;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyCondition;

@Name("Jukeboxes - Is Playing")
@Description("Checks if a jukebox is playing a record.")
@Since("1.0.4")
public class CondPlaying extends BlockStatePropertyCondition<Jukebox> {

	static {
		register(CondPlaying.class, "playing [a (song|record)]");
	}

	@Override
	protected boolean checkBlockState(Jukebox jukebox) {
		return jukebox.isPlaying();
	}

	@Override
	protected String getPropertyName() {
		return "playing";
	}

}
