package me.limeglass.khoryl.elements.block.endgateway;

import org.bukkit.Location;
import org.bukkit.block.EndGateway;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("End Gateway Exit Location")
@Description("The exit location of the end gateways.")
@Since("1.1.0")
public class ExprGatewayExitLocation extends BlockStatePropertyExpression<EndGateway, Location> {

	static {
		register(ExprGatewayExitLocation.class, Location.class, "[end] [gateway] exit location");
	}

	@Override
	@Nullable
	protected Location grab(EndGateway gateway) {
		return gateway.getExitLocation();
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
			for (EndGateway gateway : getBlockStates(event))
				gateway.setExitLocation(null);
			return;
		}
		Location location = (Location) delta[0];
		for (EndGateway gateway : getBlockStates(event))
			gateway.setExitLocation(location);
	}

	@Override
	protected String getPropertyName() {
		return "gateway exit location";
	}

}
