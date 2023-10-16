package me.limeglass.khoryl.elements.entity.merchant;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntitySyntax;

@Name("Merchant Recipes")
@Description("Gets the villagers recipes.")
@Since("1.0.0")
public class ExprMerchantRecipes extends SimpleExpression<MerchantRecipe> implements EntitySyntax<AbstractVillager> {

	static {
		Skript.registerExpression(ExprMerchantRecipes.class, MerchantRecipe.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] [merchant] recipes (from|of) %livingentities%", "[(all [[of] the]|the)] %livingentities%'[s] [merchant] recipes");
	}

	private Expression<LivingEntity> villagers;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends MerchantRecipe> getReturnType() {
		return MerchantRecipe.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		villagers = (Expression<LivingEntity>) exprs[0];
		return true;
	}

	@Override
	@Nullable
	protected MerchantRecipe[] get(Event event) {
		return Arrays.stream(villagers.getArray(event))
				.filter(entity -> entity != null)
				.filter(entity -> accepts(entity))
				.map(villager -> ((Merchant)villager).getRecipes())
				.flatMap(recipies -> recipies.stream())
				.toArray(size -> new MerchantRecipe[size]);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "recipes of " + villagers.toString(event, debug);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(MerchantRecipe[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null || delta[0] == null)
			return;
		switch (mode) {
			case SET:
				for (AbstractVillager villager : getEntities(villagers, event))
					((Merchant)villager).setRecipes(Lists.newArrayList());
			case ADD:
				for (AbstractVillager villager : getEntities(villagers, event)) {
					List<MerchantRecipe> recipes = Lists.newArrayList(((Merchant)villager).getRecipes());
					for (MerchantRecipe recipe : (MerchantRecipe[]) delta)
						recipes.add(recipe);
					((Merchant)villager).setRecipes(recipes);
				}
				break;
			case RESET:
			case DELETE:
				for (AbstractVillager villager : getEntities(villagers, event))
					((Merchant)villager).setRecipes(Lists.newArrayList());
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (AbstractVillager villager : getEntities(villagers, event)) {
					List<MerchantRecipe> recipes = Lists.newArrayList(((Merchant)villager).getRecipes());
					for (MerchantRecipe recipe : (MerchantRecipe[]) delta)
						recipes.remove(recipe);
					((Merchant)villager).setRecipes(recipes);
				}
				break;
			default:
				break;
		}
	}

}
