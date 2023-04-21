package me.limeglass.khoryl.elements.block.endgateway;

import org.bukkit.block.EndGateway;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Timespan;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("End Gateway Age")
@Description({
	"The age of the end gateway.",
	"This is used to determine when the beam is rendered."
})
@Since("1.1.0")
public class ExprGatewayAge extends BlockStatePropertyExpression<EndGateway, Timespan> {

	static {
		register(ExprGatewayAge.class, Timespan.class, "[end] gateway age");
	}

	@Override
	@Nullable
	protected Timespan grab(EndGateway gateway) {
		return Timespan.fromTicks_i(gateway.getAge());
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(Timespan.class);
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (mode == ChangeMode.DELETE || mode == ChangeMode.RESET) {
			for (EndGateway gateway : getBlockStates(event)) {
				gateway.setAge(200);
				gateway.update(true);
			}
			return;
		}
		if (delta == null)
			return;
		long ticks = ((Timespan) delta[0]).getTicks_i();
		switch (mode) {
			case REMOVE_ALL:
			case REMOVE:
				for (EndGateway gateway : getBlockStates(event)) {
					gateway.setAge(Math.max(0, gateway.getAge() - ticks));
					gateway.update(true);
				}
				break;
			case ADD:
				for (EndGateway gateway : getBlockStates(event)) {
					gateway.setAge(gateway.getAge() + ticks);
					gateway.update(true);
				}
				break;
			case SET:
				for (EndGateway gateway : getBlockStates(event)) {
					gateway.setAge(Math.max(1, ticks));
					gateway.update(true);
				}
				break;
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

	@Override
	protected String getPropertyName() {
		return "gateway age";
	}

}
