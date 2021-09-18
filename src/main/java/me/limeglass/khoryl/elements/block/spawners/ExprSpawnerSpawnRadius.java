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

@Name("Spawner Radius")
@Description("Get the spawner spawn count of a spawner.")
@Since("1.0.4")
public class ExprSpawnerSpawnRadius extends BlockStatePropertyExpression<CreatureSpawner, Integer> {

	static {
		register(ExprSpawnerSpawnRadius.class, Integer.class, "[creature] spawner [spawn] (range|radius)");
	}

	@Override
	@Nullable
	protected Integer grab(CreatureSpawner spawner) {
		return spawner.getSpawnRange();
	}

	@Override
	protected String getPropertyName() {
		return "spawner spawn radius";
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
					int exsisting = spawner.getSpawnRange();
					spawner.setSpawnRange(delay + exsisting);
					spawner.update();
				}
				break;
			case RESET:
			case DELETE:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					spawner.setSpawnRange(4);
					spawner.update();
				}
				break;
			case REMOVE:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					int exsisting = spawner.getSpawnRange();
					spawner.setSpawnRange(delay - exsisting);
					spawner.update();
				}
				break;
			case REMOVE_ALL:
				break;
			case SET:
				for (CreatureSpawner spawner : getBlockStates(event)) {
					spawner.setSpawnRange(delay);
					spawner.update();
				}
				break;
			default:
				break;
		}
	}

}
