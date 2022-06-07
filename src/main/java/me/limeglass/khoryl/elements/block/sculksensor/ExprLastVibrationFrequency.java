package me.limeglass.khoryl.elements.block.sculksensor;

import org.bukkit.block.SculkSensor;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Sculk Sensor Last Vibration Frequency")
@Description("Get the last sculk sensor vibration frequency.")
@Since("1.0.5")
public class ExprLastVibrationFrequency extends BlockStatePropertyExpression<SculkSensor, Integer> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(ExprLastVibrationFrequency.class, Integer.class, "last [sculk [shrieker]]vibration frequency", "blocks");
	}

	@Override
	@Nullable
	protected Integer grab(SculkSensor sensor) {
		return sensor.getLastVibrationFrequency();
	}

	@Override
	protected String getPropertyName() {
		return "last sculk shrieker vibration frequency";
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
		int frequency = (int) delta[0];
		switch (mode) {
			case ADD:
				for (SculkSensor sensor : getBlockStates(event)) {
					int exsting = grab(sensor);
					sensor.setLastVibrationFrequency(exsting + frequency);
					sensor.update();
				}
				break;
			case RESET:
			case DELETE:
				for (SculkSensor sensor : getBlockStates(event)) {
					sensor.setLastVibrationFrequency(0);
					sensor.update();
				}
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (SculkSensor sensor : getBlockStates(event)) {
					int exsting = grab(sensor);
					sensor.setLastVibrationFrequency(exsting - frequency);
					sensor.update();
				}
				break;
			case SET:
				for (SculkSensor sensor : getBlockStates(event)) {
					sensor.setLastVibrationFrequency(frequency);
					sensor.update();
				}
				break;
			default:
				break;
		}
	}

}
