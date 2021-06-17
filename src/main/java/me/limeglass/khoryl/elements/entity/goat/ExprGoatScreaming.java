package me.limeglass.khoryl.elements.entity.goat;

import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;
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

@Name("Goat Screaming")
@Description("Gets the goat screaming state.")
@Since("1.0.3")
public class ExprGoatScreaming extends EntityPropertyExpression<LivingEntity, Goat, Boolean> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprGoatScreaming.class, Boolean.class, "screaming", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "screaming";
	}

	@Override
	@Nullable
	protected Boolean grab(Goat goat) {
		return goat.isScreaming();
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
		Boolean screaming = (Boolean) delta[0];
		for (Goat goat : getEntities(event))
			goat.setScreaming(screaming);
	}

}
