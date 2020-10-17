package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;
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

@Name("Bee Anger Level")
@Description("Gets the anger level of bee(s).")
@Since("1.0.2")
public class ExprBeeAnger extends EntityPropertyExpression<LivingEntity, Bee, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 15)))
			register(ExprBeeAnger.class, Number.class, "[bee] anger level", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "bee anger level";
	}

	@Override
	@Nullable
	protected Number grab(Bee bee) {
		return bee.getAnger();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int anger = ((Number) delta[0]).intValue();
		for (Bee bee : getEntities(event))
			bee.setAnger(anger);
	}

}
