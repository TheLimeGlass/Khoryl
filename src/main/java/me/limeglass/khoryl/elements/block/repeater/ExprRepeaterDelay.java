package me.limeglass.khoryl.elements.block.repeater;

import org.bukkit.block.data.type.Repeater;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Repeater Delay")
@Description("Get delay of a repeater. Redstone.")
@Since("1.0.4")
public class ExprRepeaterDelay extends BlockDataPropertyExpression<Repeater, Integer> {

	static {
		register(ExprRepeaterDelay.class, Integer.class, "repeater [tick] delay");
	}

	@Override
	@Nullable
	protected Integer grab(Repeater repeater) {
		return repeater.getDelay();
	}

	@Override
	protected String getPropertyName() {
		return "repeater delay";
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
				for (Repeater repeater : getBlockDatas(event)) {
					int exsisting = repeater.getDelay();
					repeater.setDelay(delay + exsisting);
				}
				break;
			case RESET:
			case DELETE:
				for (Repeater repeater : getBlockDatas(event))
					repeater.setDelay(0);
				break;
			case REMOVE:
				for (Repeater repeater : getBlockDatas(event)) {
					int exsisting = repeater.getDelay();
					repeater.setDelay(delay - exsisting);
				}
				break;
			case REMOVE_ALL:
				break;
			case SET:
				for (Repeater repeater : getBlockDatas(event))
					repeater.setDelay(delay);
				break;
			default:
				break;
		}
	}

}
