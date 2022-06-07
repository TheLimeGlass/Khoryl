package me.limeglass.khoryl.elements.entity.warden;

import java.util.stream.Stream;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Warden;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityExpression;

@Name("Warden Anger")
@Description("Get the anger level of warden towards the entities.")
@Since("1.0.5")
public class ExprAnger extends EntityExpression<LivingEntity, Warden, Integer> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			Skript.registerExpression(ExprAnger.class, Integer.class, ExpressionType.COMBINED, "%livingentities%['[s]] (rage|anger) [level] (at|torwards|of) %entities%");
	}

	private Expression<Entity> targets;

	@Override
	public boolean isSingle() {
		return targets.isSingle();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setEntities((Expression<LivingEntity>) exprs[0]);
		targets = (Expression<Entity>) exprs[1];
		return true;
	}

	@Override
	protected @Nullable Stream<Integer> get(Event event, Warden warden) {
		return targets.stream(event).map(target -> warden.getAnger(target));
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug)
			return "anger level of warden";
		return "anger level of warden " + getEntitiesExpression().toString(event, debug) + " towards " + targets.toString(event, debug);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(Number.class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int anger = (int) delta[0];
		switch (mode) {
			case ADD:
				for (Warden warden : getEntities(event)) {
					for (Entity target : targets.getArray(event)) {
						int exsting = warden.getAnger(target);
						warden.setAnger(target, exsting + anger);
					}
				}
				break;
			case RESET:
			case DELETE:
				for (Warden warden : getEntities(event))
					for (Entity target : targets.getArray(event))
						warden.setAnger(target, 0);
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (Warden warden : getEntities(event)) {
					for (Entity target : targets.getArray(event)) {
						int exsting = warden.getAnger(target);
						warden.setAnger(target, exsting - anger);
					}
				}
				break;
			case SET:
				for (Warden warden : getEntities(event))
					for (Entity target : targets.getArray(event))
						warden.setAnger(target, anger);
				break;
			default:
				break;
		}
	}

}
