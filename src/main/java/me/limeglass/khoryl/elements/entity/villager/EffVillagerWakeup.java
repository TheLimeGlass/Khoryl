package me.limeglass.khoryl.elements.entity.villager;

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

@Name("Villager Wakeup")
@Description("Causes villagers to wake up if they're currently sleeping.")
@Since("1.0.0")
public class EffVillagerWakeup extends Effect implements EntitySyntax<Villager> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14, 4)))
			Skript.registerEffect(EffVillagerWakeup.class, "wake[ ]up villager[s] %livingentities%");
	}

	private Expression<LivingEntity> villagers;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		villagers = (Expression<LivingEntity>) exprs[0];
		return true;
	}

	@Override
	protected void execute(Event event) {
		for (LivingEntity entity : villagers.getArray(event)) {
			if (!accepts(entity))
				return;
			((Villager)entity).wakeup();
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "wake up villagers " + villagers.toString(event, debug);
	}

}
