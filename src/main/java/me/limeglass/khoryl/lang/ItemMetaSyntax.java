package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.event.Event;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;

public interface ItemMetaSyntax<S extends ItemMeta> {

	@SuppressWarnings({"serial", "unchecked"})
	default Class<S> getCastingBlockDataClass() {
		return (Class<S>) new TypeToken<S>(getClass()){}.getRawType();
	}

	@SuppressWarnings("serial")
	default <F extends ItemMeta> boolean accepts(ItemMeta data) {
		return new TypeToken<S>(getClass()){}.getRawType().isAssignableFrom(data.getClass());
	}

	@SuppressWarnings("unchecked")
	default Set<S> getMetas(Expression<ItemType> items, Event event) {
		return Arrays.stream(items.getArray(event))
				.map(item -> item.getItemMeta())
				.filter(meta -> accepts(meta))
				.map(meta -> (S)meta)
				.collect(Collectors.toSet());
	}

}
