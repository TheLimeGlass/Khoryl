package me.limeglass.khoryl.lang;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.event.Event;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class ItemMetaPropertyExpression<S extends ItemMeta, V> extends SimplePropertyExpression<ItemType, V> implements ItemMetaSyntax<S> {

	private final boolean printErrors;

	public ItemMetaPropertyExpression() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static <S extends ItemMeta, V> void register(Class<? extends ItemMetaPropertyExpression<S, V>> expression, Class<V> returnType, String property) {
		register(expression, returnType, property, "itemtypes");
	}

	@SuppressWarnings({"serial", "unchecked"})
	@Override
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	@Nullable
	protected abstract V grab(S state);

	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public final V convert(ItemType itemstack) {
		if (itemstack == null)
			return null;
		ItemMeta meta = itemstack.getItemMeta();
		if (!accepts(meta)) {
			if (printErrors)
				Skript.error("An item meta was not of type " + getCastingBlockDataClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return null;
		}
		return grab((S) meta);
	}

	@SuppressWarnings("unchecked")
	protected Map<ItemType, S> getMetaMap(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(item -> new AbstractMap.SimpleEntry<>(item, (S)item.getItemMeta()))
				.filter(entry -> accepts(entry.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
