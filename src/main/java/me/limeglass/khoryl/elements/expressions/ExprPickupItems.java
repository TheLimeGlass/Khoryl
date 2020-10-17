package me.limeglass.khoryl.elements.expressions;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

@Name("Pickup Items")
@Description("Returns whether an entity can pickup items.")
@Examples("set pickup items status of all entities to false")
@Since("1.0.2")
public class ExprPickupItems extends SimplePropertyExpression<LivingEntity, Boolean> {

	static {
		register(ExprPickupItems.class, Boolean.class, "pickup items status", "livingentities");
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	protected String getPropertyName() {
		return "pickup items status";
	}

	@Override
	@Nullable
	public Boolean convert(LivingEntity entity) {
		return entity.getCanPickupItems();
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
		boolean value = (Boolean) delta[0];
		for (LivingEntity entity : getExpr().getArray(event))
			entity.setCanPickupItems(value);
	}

}
