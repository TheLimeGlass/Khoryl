package me.limeglass.khoryl.elements.entity.merchant.recipe;

import org.bukkit.event.Event;
import org.bukkit.inventory.MerchantRecipe;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.SetEffect;

@Name("Merchant Recipe Experience Reward")
@Description("Whether to reward experience to the player within a trade of this merchant recipe.")
@Since("1.0.0")
public class SetRecipeReward extends SetEffect<MerchantRecipe> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(SetRecipeReward.class, "[give [a]] [player] experience reward", "merchantrecipes");
	}

	@Override
	protected String getPropertyName() {
		return "experience reward";
	}

	@Override
	protected void execute(Event event) {
		apply(event, (recipe, boo) -> recipe.setExperienceReward(boo));
	}

}
