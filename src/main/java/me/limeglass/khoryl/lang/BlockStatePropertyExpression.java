package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.skriptlang.skript.lang.converter.Converter;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockStatePropertyExpression<S extends BlockState, V> extends SimplePropertyExpression<Block, V> implements BlockStateSyntax<S>, Converter<Block, V> {

	private final boolean printErrors;

	public BlockStatePropertyExpression() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static <S extends BlockState, V> void register(Class<? extends BlockStatePropertyExpression<S, V>> expression, Class<V> returnType, String property) {
		SimplePropertyExpression.register(expression, returnType, property, "blocks");
	}

	@Override
	@SuppressWarnings({"serial", "unchecked"})
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	@Nullable
	protected abstract V grab(S state);

	@Override
	@Nullable
	@SuppressWarnings("unchecked")
	public final V convert(Block block) {
		if (block == null)
			return null;
		BlockState state = block.getState();
		if (!accepts(state)) {
			if (printErrors)
				Skript.error("A block state was not of type " + getCastingBlockStateClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return null;
		}
		return grab((S) state);
	}

	@SuppressWarnings("unchecked")
	protected Set<S> getBlockStates(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(block -> block.getState())
				.filter(state -> accepts(state))
				.map(state -> (S)state)
				.collect(Collectors.toSet());
	}

}
