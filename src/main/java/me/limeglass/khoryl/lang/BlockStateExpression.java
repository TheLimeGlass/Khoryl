package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.SkriptAPIException;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.util.SimpleExpression;

public abstract class BlockStateExpression<S extends BlockState, V> extends SimpleExpression<V> implements BlockStateSyntax<S> {

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
		return (V[]) getBlockStates(event).stream().flatMap(state -> Arrays.stream(grab(state))).toArray();
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
	protected Set<S> getBlockStates(Event event) {
		return Arrays.stream(blocks.getArray(event))
				.map(block -> block.getState())
				.filter(state -> accepts(state))
				.map(state -> (S)state)
				.collect(Collectors.toSet());
	}

}
