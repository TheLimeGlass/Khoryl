package me.limeglass.khoryl.lang;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockStateEffect<S extends BlockState> extends Effect implements BlockStateSyntax<S> {

	private Expression<Block> expression;
	private final boolean printErrors;

	public BlockStateEffect() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		expression = (Expression<Block>) exprs[0];
		return true;
	}

	protected Expression<Block> getExpression() {
		return expression;
	}

	protected abstract void execute(S data);

	@SuppressWarnings("unchecked")
	@Override
	protected void execute(Event event) {
		for (Block block : getExpression().getArray(event)) {
			BlockState state = block.getState();
			if (!accepts(state)) {
				if (printErrors)
					Skript.error("A block state was not of type " + getCastingBlockStateClass().getName()
							+ " in effect '" + getClass().getName() + "'", ErrorQuality.SEMANTIC_ERROR);
				continue;
			}
			S complete = (S) state;
			execute(complete);
			complete.update(true);
		}
	}

}
