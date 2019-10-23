package me.limeglass.khoryl.elements.entity.merchant.recipe;

import org.bukkit.event.Event;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;

@Name("Merchant Recipe Max Uses")
@Description("Get the maximum number of uses this trade has.")
@Since("1.0.0")
public class ExprRecipeMaxUses extends SimplePropertyExpression<MerchantRecipe, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprRecipeMaxUses.class, Number.class, "max uses", "merchantrecipes");
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	protected String getPropertyName() {
		return "max uses";
	}

	@Override
	@Nullable
	public Number convert(MerchantRecipe recipe) {
		return recipe.getMaxUses();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE || mode == ChangeMode.SET)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int uses = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (MerchantRecipe recipe : getExpr().getArray(event)) {
					int existing = recipe.getMaxUses();
					recipe.setMaxUses(existing + uses);
				}
				break;
			case REMOVE:
				for (MerchantRecipe recipe : getExpr().getArray(event)) {
					int existing = recipe.getMaxUses();
					recipe.setMaxUses(existing - uses);
				}
				break;
			case SET:
				for (MerchantRecipe recipe : getExpr().getArray(event))
					recipe.setMaxUses(uses);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

}
