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

@Name("Merchant Recipe Experience Reward")
@Description("Whether to reward experience to the player within a trade of this merchant recipe.")
@Since("1.0.0")
public class ExprRecipeReward extends SimplePropertyExpression<MerchantRecipe, Boolean> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprRecipeReward.class, Boolean.class, "[player] experience reward", "merchantrecipes");
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	protected String getPropertyName() {
		return "experience reward";
	}

	@Override
	@Nullable
	public Boolean convert(MerchantRecipe recipe) {
		return recipe.hasExperienceReward();
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
		boolean flag = (boolean) delta[0];
		for (MerchantRecipe recipe : getExpr().getArray(event))
			recipe.setExperienceReward(flag);
	}

}
