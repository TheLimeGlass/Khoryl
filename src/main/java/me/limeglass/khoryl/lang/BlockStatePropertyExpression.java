package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockStatePropertyExpression<S extends BlockState, V> extends PropertyExpression<Block, V> implements BlockStateSyntax<S>, Converter<Block, V> {

	private final boolean printErrors;
	protected Event event;

	public BlockStatePropertyExpression() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static <S extends BlockState, V> void register(Class<? extends BlockStatePropertyExpression<S, V>> expression, Class<V> returnType, String property) {
		register(expression, returnType, property, "blocks");
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<Block>) exprs[0]);
		return true;
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

	@Override
	protected V[] get(Event event, Block[] source) {
		this.event = event;
		return super.get(source, this);
	}

	@SuppressWarnings("unchecked")
	protected Set<S> getBlockStates(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(block -> block.getState())
				.filter(state -> accepts(state))
				.map(state -> (S)state)
				.collect(Collectors.toSet());
	}

	@Override
	@SuppressWarnings({"serial", "unchecked"})
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	protected abstract String getPropertyName();

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "the " + getPropertyName() + " of " + getExpr().toString(e, debug);
	}

}
