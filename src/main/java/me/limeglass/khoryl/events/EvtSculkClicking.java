package me.limeglass.khoryl.events;

import org.bukkit.GameEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.world.GenericGameEvent;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class EvtSculkClicking extends SkriptEvent {

	static {
		Skript.registerEvent("SCULK_SENSOR_TENDRILS_CLICKING", EvtSculkClicking.class, GenericGameEvent.class, "sculk [sensor] [tendrils] clicking");
		EventValues.registerEventValue(GenericGameEvent.class, Location.class, new Getter<Location, GenericGameEvent>() {
			@Override
			public @Nullable Location get(GenericGameEvent event) {
				return event.getLocation();
			}
		}, 0);
		EventValues.registerEventValue(GenericGameEvent.class, Integer.class, new Getter<Integer, GenericGameEvent>() {
			@Override
			public @Nullable Integer get(GenericGameEvent event) {
				return event.getRadius();
			}
		}, 0);
		EventValues.registerEventValue(GenericGameEvent.class, Entity.class, new Getter<Entity, GenericGameEvent>() {
			@Override
			public @Nullable Entity get(GenericGameEvent event) {
				return event.getEntity();
			}
		}, 0);
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event event) {
		return ((GenericGameEvent) event).getEvent().equals(GameEvent.SCULK_SENSOR_TENDRILS_CLICKING);
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "sculk sensor tendrils clicking";
	}

}
