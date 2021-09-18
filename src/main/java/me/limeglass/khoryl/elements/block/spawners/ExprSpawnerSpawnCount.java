package me.limeglass.khoryl.elements.block.spawners;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Spawner Spawn Count")
@Description("Get the spawner spawn count of a spawner.")
@Since("1.0.4")
public class ExprSpawnerSpawnCount extends BlockStatePropertyExpression<CreatureSpawner, Integer> {

	static {
		register(ExprSpawnerSpawnCount.class, Integer.class, "[creature] spawner spawn count");
	}

	@Override
	@Nullable
	protected Integer grab(CreatureSpawner spawner) {
		return spawner.getSpawnCount();
	}

	@Override
	protected String getPropertyName() {
		return "spawner spawn count";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode != ChangeMode.REMOVE_ALL)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int delay = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					int exsisting = spawner.getSpawnCount();
					spawner.setSpawnCount(delay + exsisting);
					spawner.update();
				}
				break;
			case RESET:
			case DELETE:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					spawner.setSpawnCount(0);
					spawner.update();
				}
				break;
			case REMOVE:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					int exsisting = spawner.getSpawnCount();
					spawner.setSpawnCount(delay - exsisting);
					spawner.update();
				}
				break;
			case REMOVE_ALL:
				break;
			case SET:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					spawner.setSpawnCount(delay);
					spawner.update();
				}
				break;
			default:
				break;
		}
	}

}
