package me.limeglass.khoryl.elements.entity.merchant.recipe;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

@Name("Merchant Recipe Ingredients")
@Since("1.0.0")
public class ExprRecipeIngredients extends SimpleExpression<ItemType> {

	static {
		Skript.registerExpression(ExprRecipeIngredients.class, ItemType.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] [merchant] ingredients (from|of) %merchantrecipes%", "[(all [[of] the]|the)] %merchantrecipes%'[s] [merchant] ingredients");
	}

	private Expression<MerchantRecipe> recipes;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends ItemType> getReturnType() {
		return ItemType.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		recipes = (Expression<MerchantRecipe>) exprs[0];
		return true;
	}

	@Override
	@Nullable
	protected ItemType[] get(Event event) {
		return Arrays.stream(recipes.getArray(event))
				.filter(recipe -> recipe != null)
				.map(recipe -> recipe.getIngredients())
				.flatMap(ingredients -> ingredients.stream())
				.map(itemstack -> new ItemType(itemstack))
				.toArray(size -> new ItemType[size]);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "ingredients of " + recipes.toString(event, debug);
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
				for (MerchantRecipe recipe : recipes.getArray(event))
					recipe.getIngredients().clear();
			case ADD:
				for (ItemType ingredient : (ItemType[]) delta) {
					for (MerchantRecipe recipe : recipes.getArray(event)) {
						recipe.addIngredient(ingredient.getRandom());
					}
				}
				break;
			case RESET:
			case DELETE:
				for (MerchantRecipe recipe : recipes.getArray(event))
					recipe.getIngredients().clear();
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (ItemType ingredient : (ItemType[]) delta) {
					for (MerchantRecipe recipe : recipes.getArray(event)) {
						recipe.getIngredients().removeIf(item -> item.isSimilar(ingredient.getRandom()));
					}
				}
				break;
			default:
				break;
		}
	}

}
