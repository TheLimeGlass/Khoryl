package me.limeglass.khoryl.elements.entity.villager;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Villager Level")
@Description("Gets the level of villagers.")
@Since("1.0.0")
public class ExprVillagerLevel extends EntityPropertyExpression<LivingEntity, Villager, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprVillagerLevel.class, Number.class, "villager level", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "villager level";
	}

	@Override
	@Nullable
	protected Number grab(Villager villager) {
		return villager.getVillagerLevel();
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
		int level = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (Villager villager : getEntities(event)) {
					int existing = villager.getVillagerLevel();
					villager.setVillagerLevel(existing + level);
				}
				break;
			case REMOVE:
				for (Villager villager : getEntities(event)) {
					int existing = villager.getVillagerLevel();
					villager.setVillagerLevel(existing - level);
				}
				break;
			case SET:
				for (Villager villager : getEntities(event))
					villager.setVillagerLevel(level);
				break;
			case DELETE:
			case RESET:
				for (Villager villager : getEntities(event))
					villager.setVillagerLevel(0);
				break;
			case REMOVE_ALL:
			default:
				break;
		}
	}

}
