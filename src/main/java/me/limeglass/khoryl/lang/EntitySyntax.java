package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.lang.Expression;

public interface EntitySyntax<P extends Entity> {

	@SuppressWarnings({"serial", "unchecked"})
	default Class<P> getCastingEntityClass() {
		return (Class<P>) new TypeToken<P>(getClass()){}.getRawType();
	}

	@SuppressWarnings("serial")
	default <F extends Entity> boolean accepts(F entity) {
		return new TypeToken<P>(getClass()){}.getRawType().isAssignableFrom(entity.getClass());
	}

	@SuppressWarnings("unchecked")
	default Set<P> getEntities(Expression<LivingEntity> villagers, Event event) {
		return Arrays.stream(villagers.getArray(event))
				.filter(entity -> accepts(entity))
				.map(entity -> (P)entity)
				.collect(Collectors.toSet());
	}

}
