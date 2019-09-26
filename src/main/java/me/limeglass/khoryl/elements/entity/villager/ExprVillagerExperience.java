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

@Name("Villager Experience")
@Description("Gets the trading experience of villagers.")
@Since("1.0.0")
public class ExprVillagerExperience extends EntityPropertyExpression<LivingEntity, Villager, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprVillagerExperience.class, Number.class, "villager [trading] experience", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "villager experience";
	}

	@Override
	@Nullable
	protected Number grab(Villager villager) {
		return villager.getVillagerExperience();
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
		int experience = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (Villager villager : getEntities(event)) {
					int existing = villager.getVillagerExperience();
					villager.setVillagerExperience(existing + experience);
				}
				break;
			case REMOVE:
				for (Villager villager : getEntities(event)) {
					int existing = villager.getVillagerExperience();
					villager.setVillagerExperience(existing - experience);
				}
				break;
			case SET:
				for (Villager villager : getEntities(event))
					villager.setVillagerExperience(experience);
				break;
			case DELETE:
			case RESET:
				for (Villager villager : getEntities(event))
					villager.setVillagerExperience(0);
				break;
			case REMOVE_ALL:
			default:
				break;
		}
	}

}
