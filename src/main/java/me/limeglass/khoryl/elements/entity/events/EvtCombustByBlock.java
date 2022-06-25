package me.limeglass.khoryl.elements.entity.events;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import ch.njol.util.Checker;

@Name("Entity Combust By Block")
@Description("Called when a block causes an entity to combust (aka burn or take fire damage).")
@Since("1.0.6")
public class EvtCombustByBlock extends SkriptEvent {

	static {
		Skript.registerEvent("combust by block", EvtCombustByBlock.class, EntityCombustByBlockEvent.class, "entit(y|ies) combust[ing] (from|by) (%-itemtypes/blockdatas%|block)");
		EventValues.registerEventValue(EntityCombustByBlockEvent.class, Entity.class, new Getter<>() {
			@Override
			public @Nullable Entity get(EntityCombustByBlockEvent event) {
				return event.getEntity();
			}
		}, 0);
	}

	private Literal<Object> types;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		types = (Literal<Object>) args[0];
		return true;
	}

	@Override
	public boolean check(Event e) {
		if (types == null)
			return true;
		EntityCombustByBlockEvent event = (EntityCombustByBlockEvent) e;
		return types.check(event, new Checker<>() {
			@Override
			public boolean check(Object object) {
				Block combuster = event.getCombuster();
				if (combuster == null)
					return false;
				if (object instanceof ItemType)
					return ((ItemType) object).isOfType(combuster);
				else if (combuster instanceof BlockData)
					return combuster.getBlockData().matches(((BlockData) object));
				return false;
			}
		});
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug || types == null)
			return "entity combusting by block";
		return "entity combusting by itemtypes/blockdatas " + Classes.toString(types);
	}

}
