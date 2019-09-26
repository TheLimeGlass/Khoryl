package me.limeglass.khoryl.elements.entity.merchant.recipe;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.event.Event;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

@Name("Merchant Recipe")
@Description("Create a new merchant recipe for villagers.")
@Examples("set {_recipe} to a new merchant recipe with result paper with lore \"&6Elder Scroll\" and 5 max uses from ingredients diamond and 5 sugar cane")
@Since("1.0.0")
public class ExprMerchantRecipe extends SimpleExpression<MerchantRecipe> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			Skript.registerExpression(ExprMerchantRecipe.class, MerchantRecipe.class, ExpressionType.COMBINED, 
					"[new] merchant recipe with result %itemtype% and %number% max uses",
					"[new] merchant recipe with result %itemtype%[,] [and] with %number% max uses (and|from) ingredients %itemtypes%");
	}

	private Expression<ItemType> itemType, ingredients;
	private Expression<Number> uses;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends MerchantRecipe> getReturnType() {
		return MerchantRecipe.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		itemType = (Expression<ItemType>) exprs[0];
		uses = (Expression<Number>) exprs[1];
		if (matchedPattern == 1)
			ingredients = (Expression<ItemType>) exprs[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "merchant recipe " + itemType.toString(event, debug) + " with max uses " + uses.toString(event, debug);
	}

	@Override
	@Nullable
	protected MerchantRecipe[] get(Event event) {
		ItemType item = itemType.getSingle(event);
		Number max = uses.getSingle(event);
		if (itemType == null || max == null)
			return null;
		MerchantRecipe recipe = new MerchantRecipe(item.getRandom(), max.intValue());
		if (ingredients != null)
			recipe.setIngredients(Arrays.stream(ingredients.getArray(event)).map(it -> it.getRandom()).collect(Collectors.toList()));
		return CollectionUtils.array(recipe);
	}

}
