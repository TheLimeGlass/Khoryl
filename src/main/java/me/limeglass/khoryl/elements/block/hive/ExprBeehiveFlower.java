package me.limeglass.khoryl.elements.block.hive;

import org.bukkit.Location;
import org.bukkit.block.Beehive;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Beehive Flower")
@Description("Get the hive's flower location.")
@Since("1.0.3")
public class ExprBeehiveFlower extends BlockStatePropertyExpression<Beehive, Location> {

	static {
		if (Skript.methodExists(Beehive.class, "getFlower"))
			register(ExprBeehiveFlower.class, Location.class, "[bee[ ]hive] flower [location]");
	}

	@Override
	@Nullable
	protected Location grab(Beehive beehive) {
		return beehive.getFlower();
	}

	@Override
	protected String getPropertyName() {
		return "beehive flower location";
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
		if (delta == null || delta[0] == null)
			return;
		Location location = (Location) delta[0];
		for (Beehive beehive : getBlockStates(event)) {
			beehive.setFlower(location);
			beehive.update();
		}
	}

}
