package me.limeglass.khoryl.elements.entity.merchant.recipe;

import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Version;

@Name("Merchant Recipe Result")
@Since("1.0.0")
public class ExprRecipeResult extends SimplePropertyExpression<MerchantRecipe, ItemType> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprRecipeResult.class, ItemType.class, "result", "merchantrecipies");
	}

	@Override
	public Class<? extends ItemType> getReturnType() {
		return ItemType.class;
	}

	@Override
	protected String getPropertyName() {
		return "result";
	}

	@Override
	@Nullable
	public ItemType convert(MerchantRecipe recipe) {
		return new ItemType(recipe.getResult());
	}

}
