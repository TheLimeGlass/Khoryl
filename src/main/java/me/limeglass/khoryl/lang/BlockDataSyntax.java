package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.lang.Expression;

public interface BlockDataSyntax<S extends BlockData> {

	@SuppressWarnings({"serial", "unchecked"})
	default Class<S> getCastingBlockDataClass() {
		return (Class<S>) new TypeToken<S>(getClass()){}.getRawType();
	}

	@SuppressWarnings("serial")
	default <F extends BlockData> boolean accepts(BlockData data) {
		return new TypeToken<S>(getClass()){}.getRawType().isAssignableFrom(data.getClass());
	}

	@SuppressWarnings("unchecked")
	default Set<S> getStates(Expression<Block> blocks, Event event) {
		return Arrays.stream(blocks.getArray(event))
				.map(block -> block.getBlockData())
				.filter(data -> accepts(data))
				.map(data -> (S)data)
				.collect(Collectors.toSet());
	}

}
