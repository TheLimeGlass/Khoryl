package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;

public abstract class BlockEffect<S extends BlockState> extends Effect implements BlockStateSyntax<S> {

	@SuppressWarnings({"serial", "unchecked"})
	public Class<? extends S> getBlockStateType() {
		return (Class<? extends S>) new TypeToken<S>(getClass()){}.getRawType();
	}

	@SuppressWarnings("unchecked")
	protected Set<S> getBlockStates(Expression<Block> expression, Event event) {
		return Arrays.stream(expression.getArray(event))
				.map(block -> block.getState())
				.filter(state -> accepts(state))
				.map(state -> (S)state)
				.collect(Collectors.toSet());
	}

}
