package me.limeglass.khoryl.elements.entity.endercrystal;

import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Ender Crystal Beam Target")
@Description({
	"The location ender crystal beams are pointing at.",
	"Resetting this expression will make it not beam."
})
@Since("1.1.0")
public class ExprEnderCrystalBeamLocation extends EntityPropertyExpression<Entity, EnderCrystal, Location> {

	static {
		register(ExprEnderCrystalBeamLocation.class, Location.class, "[crystal] beam (target|location)");
	}

	@Override
	@Nullable
	protected Location grab(EnderCrystal crystal) {
		return crystal.getBeamTarget();
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		switch (mode) {
			case DELETE:
			case SET:
				return CollectionUtils.array(Location.class);
			case ADD:
			case REMOVE:
			case REMOVE_ALL:
			case RESET:
			default:
				return null;
		}
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (mode == ChangeMode.DELETE) {
			for (EnderCrystal crystal : getEntities(event))
				crystal.setBeamTarget(null);
			return;
		}
		Location location = (Location) delta[0];
		for (EnderCrystal crystal : getEntities(event))
			crystal.setBeamTarget(location);
	}

	@Override
	protected String getPropertyName() {
		return "beam target";
	}

}
