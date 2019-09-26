package me.limeglass.khoryl.elements.entity.merchant.recipe;

import org.bukkit.event.Event;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;

@Name("Merchant Recipe Ingredients")
@Since("1.0.0")
public class ExprRecipeIngredients extends SimplePropertyExpression<MerchantRecipe, ItemType[]> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprRecipeIngredients.class, ItemType[].class, "ingredients", "merchantrecipies");
	}

	@Override
	public Class<? extends ItemType[]> getReturnType() {
		return ItemType[].class;
	}

	@Override
	protected String getPropertyName() {
		return "ingredients";
	}

	@Override
	@Nullable
	public ItemType[] convert(MerchantRecipe recipe) {
		return recipe.getIngredients().stream()
				.map(ingredient -> new ItemType(ingredient))
				.toArray(size -> new ItemType[size]);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(ItemType.class, ItemType[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null || delta[0] == null)
			return;
		switch (mode) {
			case SET:
				for (MerchantRecipe recipe : getExpr().getArray(event))
					recipe.getIngredients().clear();
			case ADD:
				for (ItemType ingredient : (ItemType[]) delta) {
					for (MerchantRecipe recipe : getExpr().getArray(event)) {
						recipe.addIngredient(ingredient.getRandom());
					}
				}
				break;
			case RESET:
			case DELETE:
				for (MerchantRecipe recipe : getExpr().getArray(event))
					recipe.getIngredients().clear();
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (ItemType ingredient : (ItemType[]) delta) {
					for (MerchantRecipe recipe : getExpr().getArray(event)) {
						recipe.getIngredients().removeIf(item -> item.isSimilar(ingredient.getRandom()));
					}
				}
				break;
			default:
				break;
		}
	}

}
