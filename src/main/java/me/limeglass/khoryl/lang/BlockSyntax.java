package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.lang.Expression;

public interface BlockSyntax<S extends BlockState> {

	@SuppressWarnings({"serial", "unchecked"})
	default Class<S> getCastingBlockStateClass() {
		return (Class<S>) new TypeToken<S>(getClass()){}.getRawType();
	}

	@SuppressWarnings("serial")
	default <F extends BlockState> boolean accepts(BlockState state) {
		return new TypeToken<S>(getClass()){}.getRawType().isAssignableFrom(state.getClass());
	}

	@SuppressWarnings("unchecked")
	default Set<S> getStates(Expression<Block> blocks, Event event) {
		return Arrays.stream(blocks.getArray(event))
				.map(block -> block.getState())
				.filter(state -> accepts(state))
				.map(state -> (S)state)
				.collect(Collectors.toSet());
	}

}
