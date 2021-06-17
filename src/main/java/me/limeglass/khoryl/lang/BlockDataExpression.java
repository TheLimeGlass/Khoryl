package me.limeglass.khoryl.lang;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.SkriptAPIException;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.util.SimpleExpression;

public abstract class BlockDataExpression<S extends BlockData, V> extends SimpleExpression<V> implements BlockDataSyntax<S> {

	private Expression<Block> blocks;

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	protected V[] get(Event event) {
		if (blocks == null)
			throw new SkriptAPIException("Blocks expression from the BlockStateExpression was not set.");
		return (V[]) getBlockDatas(event).stream().flatMap(state -> Arrays.stream(grab(state))).toArray();
	}

	@Nullable
	protected abstract V[] grab(S state);

	protected final void setBlockExpression(Expression<Block> blocks) {
		this.blocks = blocks;
	}

	protected final Expression<Block> getBlockExpression() {
		return blocks;
	}

	@SuppressWarnings("unchecked")
	protected Set<S> getBlockDatas(Event event) {
		return Arrays.stream(blocks.getArray(event))
				.map(block -> block.getBlockData())
				.filter(data -> accepts(data))
				.map(data -> (S)data)
				.collect(Collectors.toSet());
	}

	@SuppressWarnings("unchecked")
	protected Map<Block, S> getBlockDatasMap(Event event) {
		return Arrays.stream(blocks.getArray(event))
				.map(block -> new AbstractMap.SimpleEntry<>(block, (S)block.getBlockData()))
				.filter(entry -> accepts(entry.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
