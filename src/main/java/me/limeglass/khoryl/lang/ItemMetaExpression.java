package me.limeglass.khoryl.lang;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public abstract class ItemMetaExpression<M extends ItemMeta, V> extends PropertyExpression<ItemStack, V> implements ItemMetaSyntax<M> {

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	public static <S extends ItemMeta, V> void register(Class<? extends ItemMetaExpression<S, V>> expression, Class<V> returnType, String property) {
		register(expression, returnType, property, "itemstacks");
	}

	@Nullable
	protected abstract V[] grab(M meta);

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends ItemStack>) exprs[0]);
		return true;
	}

	protected abstract String getPropertyName();

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return getPropertyName() + " of " + getExpr().toString(event, debug);
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
	protected Map<ItemStack, M> getMetasMap(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(item -> new AbstractMap.SimpleEntry<>(item, (M)item.getItemMeta()))
				.filter(entry -> accepts(entry.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected V[] get(Event event, ItemStack[] items) {
		return (V[]) Arrays.stream(items)
				.map(item -> item.getItemMeta())
				.filter(meta -> accepts(meta))
				.map(meta -> grab((M)meta))
				.toArray();
	}

}
