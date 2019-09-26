package me.limeglass.khoryl.elements.entity.villager;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.EntitySyntax;

@Name("Villager Sleep")
@Description("Attempts to make villagers sleep at a given location.")
@Since("1.0.0")
public class EffVillagerSleep extends Effect implements EntitySyntax<Villager> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14, 4)))
			Skript.registerEffect(EffVillagerSleep.class, "(force|make) villager %livingentity% sleep at %location%");
	}

	private Expression<LivingEntity> villager;
	private Expression<Location> location;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		villager = (Expression<LivingEntity>) exprs[0];
		location = (Expression<Location>) exprs[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		LivingEntity entity = villager.getSingle(event);
		if (!accepts(entity))
			return;
		((Villager)entity).sleep(location.getSingle(event));
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "forcing villager " + villager.toString(event, debug) + " to sleep at " + location.toString(event, debug);
	}

}
