package me.limeglass.khoryl.elements.expressions;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;

@Name("AI")
@Description("Returns whether an entity has AI.")
@Examples("set artificial intelligence of target entity to false")
@Since("1.0.0")
public class ExprAI extends SimplePropertyExpression<LivingEntity, Boolean> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			register(ExprAI.class, Boolean.class, "(ai|artificial intelligence)", "livingentities");
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	protected String getPropertyName() {
		return "artificial intelligence";
	}

	@Override
	@Nullable
	public Boolean convert(LivingEntity entity) {
		return entity.hasAI();
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
			entity.setAI(value);
	}

}
