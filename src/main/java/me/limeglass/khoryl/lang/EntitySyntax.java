package me.limeglass.khoryl.lang;

import org.bukkit.entity.Entity;

import com.google.common.reflect.TypeToken;

public interface EntitySyntax<P extends Entity> {

	@SuppressWarnings({"serial", "unchecked"})
	default Class<P> getCastingEntityClass() {
		return (Class<P>) new TypeToken<P>(getClass()){}.getRawType();
	}

	@SuppressWarnings("serial")
	default <F extends Entity> boolean accepts(F entity) {
		return new TypeToken<P>(getClass()){}.getRawType().isAssignableFrom(entity.getClass());
	}

}
