package me.limeglass.khoryl.events;

import org.bukkit.GameEvent;
import org.bukkit.event.Event;
import org.bukkit.event.world.GenericGameEvent;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public class EvtSculkShrieking extends SkriptEvent {

	static {
		Skript.registerEvent("SHRIEK", EvtSculkShrieking.class, GenericGameEvent.class, "sculk [shrieker] shriek[ing]");
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event event) {
		return ((GenericGameEvent) event).getEvent().equals(GameEvent.SHRIEK);
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "sculk shrieker shrieking";
	}

}
