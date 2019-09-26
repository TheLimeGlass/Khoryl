package me.limeglass.khoryl.elements.entity.merchant.recipe;

import org.bukkit.inventory.MerchantRecipe;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;

@Name("Merchant Recipe Has Experience Reward")
@Description("Whether to reward experience to the player within a trade of this merchant recipe.")
@Since("1.0.0")
public class CondRecipeReward extends PropertyCondition<MerchantRecipe> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(CondRecipeReward.class, PropertyType.HAVE, "[player] experience reward", "merchantrecipes");
	}

	@Override
	public boolean check(MerchantRecipe recipe) {
		return recipe.hasExperienceReward();
	}

	@Override
	protected String getPropertyName() {
		return "experience reward";
	}

}
