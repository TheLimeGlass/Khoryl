package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
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

@Name("Bee Hive")
@Description("Gets the hive location of bee(s).")
@Since("1.0.2")
public class ExprBeeHive extends EntityPropertyExpression<LivingEntity, Bee, Location> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 15)))
			register(ExprBeeHive.class, Location.class, "hive location", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "hive location";
	}

	@Override
	@Nullable
	protected Location grab(Bee bee) {
		return bee.getHive();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Location.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Location location = (Location) delta[0];
		for (Bee bee : getEntities(event))
			bee.setHive(location);
	}

}
