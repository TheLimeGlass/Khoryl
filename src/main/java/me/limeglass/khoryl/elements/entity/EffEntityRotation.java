package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;

@Name("Entity Rotation")
@Description("Set the rotation of entities.")
@Examples("set yaw and pitch of target entity to 6 and 9")
@Since("1.0.4")
public class EffEntityRotation extends Effect {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			Skript.registerEffect(EffEntityRotation.class, "set (1¦yaw|2¦pitch) of %entities% to %number%",
					"set (yaw and pitch|rotation) of %entities% to %number% and %number%",
					"set yaw of %entities% to %number% and pitch to %number%");
	}

	private Expression<Number> first, second;
	private Expression<Entity> entities;
	private boolean both, yaw;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		entities = (Expression<Entity>) exprs[0];
		both = matchedPattern != 0;
		yaw = parseResult.mark == 1;
		first = (Expression<Number>) exprs[1];
		second = (Expression<Number>) exprs[2];
		return true;
	}

	@Override
	protected void execute(Event event) {
		for (Entity entity : entities.getArray(event)) {
			if (!both) {
				if (yaw) {
					float pitch = entity.getLocation().getPitch();
					entity.setRotation(first.getSingle(event).floatValue(), pitch);
				} else {
					float yaw = entity.getLocation().getYaw();
					entity.setRotation(yaw, first.getSingle(event).floatValue());
				}
			} else {
				entity.setRotation(second.getSingle(event).floatValue(), first.getSingle(event).floatValue());
			}
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "entity rotation";
		if (both) {
			if (yaw)
				return "set yaw of " + entities.toString(event, debug) + " to " + first.toString(event, debug);
			return "set pitch of " + entities.toString(event, debug) + " to " + first.toString(event, debug);
		}
		return "rotation of " + entities.toString(event, debug) + " with yaw " + first.toString(event, debug) + " and pitch " + second.toString(event, debug);
	}

}
