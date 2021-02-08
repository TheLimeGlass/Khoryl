package me.limeglass.khoryl.elements.block.beacon;

import org.bukkit.block.Beacon;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Beacon Secondary Effect")
@Description("Get the secondary potion effect of a beacon.")
@Since("1.0.3")
public class ExprBeaconSecondaryEffect extends BlockStatePropertyExpression<Beacon, PotionEffect> {

	static {
		register(ExprBeaconSecondaryEffect.class, PotionEffect.class, "second[ary] [beacon] [potion] effect[s] ", "blocks");
	}

	@Override
	@Nullable
	protected PotionEffect grab(Beacon beacon) {
		return beacon.getSecondaryEffect();
	}

	@Override
	protected String getPropertyName() {
		return "secondary beacon potion effect";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(PotionEffectType.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null || delta[0] == null)
			return;
		PotionEffectType type = (PotionEffectType) delta[0];
		for (Beacon beacon : getBlockStates(event)) {
			beacon.setSecondaryEffect(type);
			beacon.update();
		}
	}

}
