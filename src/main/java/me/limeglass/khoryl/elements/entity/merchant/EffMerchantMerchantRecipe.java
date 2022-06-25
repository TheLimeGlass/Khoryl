package me.limeglass.khoryl.elements.entity.merchant;

import java.util.List;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.EntitySyntax;

@Name("Merchant Recipes")
@Description("Set/removes the spot the recipe should be at.")
@Since("1.0.0")
public class EffMerchantMerchantRecipe extends Effect implements EntitySyntax<AbstractVillager> {

	static {
		Skript.registerEffect(EffMerchantMerchantRecipe.class, "(1¦set|2¦remove) [merchant] recipe at [index] %number% [to %merchantrecipe%] (for|of) [villager] %livingentity%");
	}

	private Expression<MerchantRecipe> recipe;
	private Expression<LivingEntity> villager;
	private Expression<Number> index;
	private boolean set;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		set = parseResult.mark == 1;
		index = (Expression<Number>) exprs[0];
		recipe = (Expression<MerchantRecipe>) exprs[1];
		villager = (Expression<LivingEntity>) exprs[2];
		return true;
	}

	@Override
	protected void execute(Event event) {
		LivingEntity entity = villager.getSingle(event);
		if (!accepts(entity))
			return;
		AbstractVillager villagerEntity = (AbstractVillager) entity;
		Number number = index.getSingle(event);
		if (number == null)
			return;
		if (set) {
			MerchantRecipe merchantRecipe = recipe.getSingle(event);
			if (merchantRecipe == null)
				return;
			villagerEntity.setRecipe(number.intValue(), merchantRecipe);
			return;
		}
		List<MerchantRecipe> recipes = villagerEntity.getRecipes();
		if (recipes.size() >= number.intValue() || number.intValue() < 0)
			return;
		recipes.remove(number.intValue());
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return set ? "setting " : "removing " + "of recipe at " + index.toString(event, debug) + " for villager " + villager.toString(event, debug);
	}

}
