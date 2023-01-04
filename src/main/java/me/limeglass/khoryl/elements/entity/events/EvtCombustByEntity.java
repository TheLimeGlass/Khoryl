package me.limeglass.khoryl.elements.entity.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.util.Checker;

@Name("Entity Combust By Entity")
@Description("Called when an entity causes another entity to combust.")
@Since("1.0.6")
public class EvtCombustByEntity extends SkriptEvent {

	static {
		Skript.registerEvent("combust by entity", EvtCombustByEntity.class, EntityCombustByEntityEvent.class, "entit(y|ies) combust[ing] (from|by) (%-entitydatas%|[another] entity)");
		EventValues.registerEventValue(EntityCombustByEntityEvent.class, Entity.class, new Getter<Entity, EntityCombustByEntityEvent>() {
			@Override
			public @Nullable Entity get(EntityCombustByEntityEvent event) {
				return event.getEntity();
			}
		}, 0);
	}

	private Literal<EntityData<?>> datas;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		datas = (Literal<EntityData<?>>) args[0];
		return true;
	}

	@Override
	public boolean check(Event e) {
		if (datas == null)
			return true;
		EntityCombustByEntityEvent event = (EntityCombustByEntityEvent) e;
		return datas.check(event, new Checker<EntityData<?>>() {
			@Override
			public boolean check(EntityData<?> data) {
				return data.isInstance(event.getCombuster());
			}
		});
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug || datas == null)
			return "entity combusting by entity";
		return "entity combusting by entitydatas " + Classes.toString(datas);
	}

}
