package me.limeglass.khoryl.elements.block.sculksensor;

import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.block.data.type.SculkSensor.Phase;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockDataPropertyExpression;

@Name("Sculk Sensor Phase")
@Description("Get the current phase of the sculk sensor.")
@Since("1.0.5")
public class ExprPhase extends BlockDataPropertyExpression<SculkSensor, Phase> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(ExprPhase.class, Phase.class, "[sculk sensor] phase", "blocks");
	}

	@Override
	@Nullable
	protected Phase grab(SculkSensor sensor) {
		return sensor.getPhase();
	}

	@Override
	protected String getPropertyName() {
		return "sculker phase";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Phase.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Phase phase = (Phase) delta[0];
		for (SculkSensor sensor : getBlockDatas(event))
			sensor.setPhase(phase);
	}

}
