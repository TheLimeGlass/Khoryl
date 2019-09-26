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

@Name("Merchant Recipe Experience")
@Description("Gets the amount of experience the villager earns from the merchant recipe in trades.")
@Since("1.0.0")
public class ExprRecipeExperience extends SimplePropertyExpression<MerchantRecipe, Number> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprRecipeExperience.class, Number.class, "villager experience", "merchantrecipies");
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	protected String getPropertyName() {
		return "villager experience";
	}

	@Override
	@Nullable
	public Number convert(MerchantRecipe recipe) {
		return recipe.getVillagerExperience();
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
		int experience = ((Number) delta[0]).intValue();
		switch (mode) {
			case ADD:
				for (MerchantRecipe recipe : getExpr().getArray(event)) {
					int existing = recipe.getVillagerExperience();
					recipe.setVillagerExperience(existing + experience);
				}
				break;
			case REMOVE:
				for (MerchantRecipe recipe : getExpr().getArray(event)) {
					int existing = recipe.getVillagerExperience();
					recipe.setVillagerExperience(existing - experience);
				}
				break;
			case SET:
				for (MerchantRecipe recipe : getExpr().getArray(event))
					recipe.setVillagerExperience(experience);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
			default:
				break;
		}
	}

}
