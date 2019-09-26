package me.limeglass.khoryl.elements.entity.vex;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vex;
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

@Name("Vex Charging")
@Description("Gets the charging state of vexes.")
@Since("1.0.0")
public class ExprCharging extends EntityPropertyExpression<LivingEntity, Vex, Boolean> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(ExprCharging.class, Boolean.class, "vex charg(ing|e)", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "vex charging";
	}

	@Override
	@Nullable
	protected Boolean grab(Vex vex) {
		return vex.isCharging();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		boolean charging = (Boolean) delta[0];
		for (Vex vex : getEntities(event))
			vex.setCharging(charging);
	}

}
