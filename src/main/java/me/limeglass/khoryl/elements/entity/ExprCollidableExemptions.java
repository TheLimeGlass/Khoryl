package me.limeglass.khoryl.elements.entity;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

@Name("Collidable Exemptions")
@Description({"Gets a mutable set of UUIDs of the entities which are exempt from the entity's collidable rule and which's collision with this entity will behave the opposite of it.",
		"For example if collidable is true and an entity is in the exemptions set then it will not collide with it.",
		"Similarly if collidable is false and an entity is in this set then it will still collide with it."})
@Examples("add player to collidable exemptions of event-entity")
@Since("1.0.6")
public class ExprCollidableExemptions extends SimpleExpression<LivingEntity> {

	static {
		if (Skript.methodExists(LivingEntity.class, "getCollidableExemptions"))
			Skript.registerExpression(ExprCollidableExemptions.class, LivingEntity.class, ExpressionType.PROPERTY, "[the] collidable exemptions [of %livingentities%]", "%livingentities%'[s] collidable exemptions");
	}

	private Expression<LivingEntity> entities;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends LivingEntity> getReturnType() {
		return LivingEntity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		entities = (Expression<LivingEntity>) exprs[0];
		return true;
	}

	@Override
	protected @Nullable LivingEntity[] get(Event event) {
		return entities.stream(event)
				.flatMap(entity -> entity.getCollidableExemptions().stream())
				.map(uuid -> Bukkit.getEntity(uuid))
				.toArray(LivingEntity[]::new);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null || debug)
			return "collidable exemptions";
		return "collidable exemptions of " + entities.toString(event, debug);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(LivingEntity.class, LivingEntity[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null)
			return;
		Set<UUID> uuids = Stream.of((LivingEntity[]) delta)
				.map(entity -> entity.getUniqueId())
				.collect(Collectors.toSet());
		switch (mode) {
			case ADD:
				for (LivingEntity entity : entities.getArray(event))
					entity.getCollidableExemptions().addAll(uuids);
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (LivingEntity entity : entities.getArray(event))
					entity.getCollidableExemptions().removeAll(uuids);
				break;
			case SET:
				for (LivingEntity entity : entities.getArray(event)) {
					entity.getCollidableExemptions().removeAll(uuids);
					entity.getCollidableExemptions().addAll(uuids);
				}
				break;
			case DELETE:
			case RESET:
				for (LivingEntity entity : entities.getArray(event))
					entity.getCollidableExemptions().clear();
			default:
				break;
		}
	}

}
