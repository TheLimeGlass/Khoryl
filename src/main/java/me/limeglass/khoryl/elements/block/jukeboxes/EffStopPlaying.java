package me.limeglass.khoryl.elements.block.jukeboxes;

import org.bukkit.block.Block;
import org.bukkit.block.Jukebox;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.BlockStateSyntax;

@Name("Jukeboxes - Stop Playing")
@Description("Stop a jukebox from playing a record.")
@Since("1.0.4")
public class EffStopPlaying extends Effect implements BlockStateSyntax<Jukebox> {

	static {
		Skript.registerEffect(EffStopPlaying.class, "stop playing record in [jukebox[es]] %blocks%");
	}

	private Expression<Block> jukeboxes;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		jukeboxes = (Expression<Block>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "stop playing record";
		return "stop playing record in jukeboxes " + jukeboxes.toString(event, debug);
	}

	@Override
	protected void execute(Event event) {
		for (Jukebox jukebox : getStates(jukeboxes, event)) {
			jukebox.stopPlaying();
			jukebox.update();
		}
	}

}
