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
	protected Event event;

	@Override
	@SuppressWarnings({"unchecked", "serial"})
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	@Override
	@Nullable
	@SuppressWarnings("unchecked")
	protected V[] get(Event event) {
		this.event = event;
		if (blocks == null)
			throw new SkriptAPIException("Blocks expression from the BlockStateExpression was not set.");
		return (V[]) getBlockStates(event).stream().map(state -> grab(state)).toArray();
	}

	@Override
	public boolean isSingle() {
		return blocks.isSingle();
	}

	@Nullable
	protected abstract V grab(S state);

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
