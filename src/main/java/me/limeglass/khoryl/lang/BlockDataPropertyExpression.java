package me.limeglass.khoryl.lang;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class BlockDataPropertyExpression<S extends BlockData, V> extends SimplePropertyExpression<Block, V> implements BlockDataSyntax<S> {

	private final boolean printErrors;

	public BlockDataPropertyExpression() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static <S extends BlockData, V> void register(Class<? extends BlockDataPropertyExpression<S, V>> expression, Class<V> returnType, String property) {
		register(expression, returnType, property, "blocks");
	}

	@SuppressWarnings({"serial", "unchecked"})
	@Override
	public Class<? extends V> getReturnType() {
		return (Class<? extends V>) new TypeToken<V>(getClass()){}.getRawType();
	}

	@Nullable
	protected abstract V grab(S state);

	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public final V convert(Block block) {
		if (block == null)
			return null;
		BlockData data = block.getBlockData();
		if (!accepts(data)) {
			if (printErrors)
				Skript.error("A block data was not of type " + getCastingBlockDataClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return null;
		}
		return grab((S) data);
	}

	@SuppressWarnings("unchecked")
	protected Map<Block, S> getBlockDatasMap(Event event) {
		return Arrays.stream(getExpr().getArray(event))
				.map(block -> new AbstractMap.SimpleEntry<>(block, (S)block.getBlockData()))
				.filter(entry -> accepts(entry.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
