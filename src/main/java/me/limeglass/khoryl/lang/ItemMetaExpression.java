package me.limeglass.khoryl.lang;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.event.Event;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public abstract class ItemMetaExpression<M extends ItemMeta, V> extends PropertyExpression<ItemType, V> implements ItemMetaSyntax<M> {

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	public static <S extends ItemMeta, V> void register(Class<? extends ItemMetaExpression<S, V>> expression, Class<V> returnType, String property) {
		register(expression, returnType, property, "itemtypes");
	}

	@Nullable
	protected abstract V[] grab(M meta);

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends ItemType>) exprs[0]);
		return true;
	}

	protected abstract String getPropertyName();

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "the " + getPropertyName() + " of " + getExpr().toString(e, debug);
	}

	@SuppressWarnings("unchecked")
	protected Set<M> getMetas(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(item -> item.getItemMeta())
				.filter(meta -> accepts(meta))
				.map(meta -> (M)meta)
				.collect(Collectors.toSet());
	}

	@SuppressWarnings("unchecked")
	protected Map<ItemType, M> getMetasMap(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(item -> new AbstractMap.SimpleEntry<>(item, (M)item.getItemMeta()))
				.filter(entry -> accepts(entry.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected V[] get(Event event, ItemType[] items) {
		return (V[]) Arrays.stream(items)
				.map(item -> item.getItemMeta())
				.filter(meta -> accepts(meta))
				.map(meta -> grab((M)meta))
				.toArray();
	}

}
