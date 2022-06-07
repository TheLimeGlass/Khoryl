package me.limeglass.khoryl.elements.block.analoguepowerable;

import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Analogue Powerable Power")
@Description("Get the power state for analogue powerable blocks.")
@Since("1.0.5")
public class ExprPower extends BlockDataPropertyExpression<AnaloguePowerable, Integer> {

	static {
		if (Skript.methodExists(AnaloguePowerable.class, "getPower"))
			register(ExprPower.class, Integer.class, "[analogue] [redstone] [signal] power", "blocks");
	}

	@Override
	@Nullable
	protected Integer grab(AnaloguePowerable powerable) {
		return powerable.getPower();
	}

	@Override
	protected String getPropertyName() {
		return "redstone signal power";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(Number.class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int power = (int) delta[0];
		switch (mode) {
			case ADD:
				for (AnaloguePowerable powerable : getBlockDatas(event)) {
					int exsting = grab(powerable);
					powerable.setPower(exsting + power);
				}
				break;
			case RESET:
			case DELETE:
				for (AnaloguePowerable powerable : getBlockDatas(event))
					powerable.setPower(1);
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (AnaloguePowerable powerable : getBlockDatas(event)) {
					int exsting = grab(powerable);
					powerable.setPower(exsting - power);
				}
				break;
			case SET:
				for (AnaloguePowerable powerable : getBlockDatas(event))
					powerable.setPower(power);
				break;
			default:
				break;
		}
	}

}
