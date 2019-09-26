package me.limeglass.khoryl.elements.entity.villager;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Type;
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

@Name("Villager Type")
@Description("Gets the type of villagers. Plains, Snow etc.")
@Since("1.0.0")
/**
 * Skript "should" have proper support for getting this, but at the moment it doesn't.
 */
public class ExprVillagerType extends EntityPropertyExpression<LivingEntity, Villager, Type> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprVillagerType.class, Type.class, "villager type", "livingentities");
	}

	@Override
	@Nullable
	protected Type grab(Villager villager) {
		return villager.getVillagerType();
	}

	@Override
	protected String getPropertyName() {
		return "villager type";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Type.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Type type = (Type) delta[0];
		for (Villager villager : getEntities(event))
			villager.setVillagerType(type);
	}

}
