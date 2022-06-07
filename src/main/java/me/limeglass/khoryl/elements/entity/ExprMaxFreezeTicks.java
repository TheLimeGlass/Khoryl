package me.limeglass.khoryl.elements.entity;

import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.util.Version;

@Name("Max Freeze Ticks")
@Description("Returns the max amount of time an entity has been frozen in powdered snow.")
@Examples("set {_ticks} to frozen time of target entity")
@Since("1.0.4")
public class ExprMaxFreezeTicks extends SimplePropertyExpression<Entity, Timespan> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprMaxFreezeTicks.class, Timespan.class, "max[imum] (frozen|freez(e|ing)) (time|tick[s])", "entities");
	}

	@Override
	public Class<? extends Timespan> getReturnType() {
		return Timespan.class;
	}

	@Override
	protected String getPropertyName() {
		return "max freeze ticks";
	}

	@Override
	@Nullable
	public Timespan convert(Entity entity) {
		return Timespan.fromTicks_i(entity.getMaxFreezeTicks());
	}

}
