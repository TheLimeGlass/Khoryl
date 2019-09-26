package me.limeglass.khoryl.elements.entity.merchant;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Merchant Recipes")
@Description("Gets the villagers recipies.")
@Since("1.0.0")
public class ExprMerchantRecipes extends EntityPropertyExpression<LivingEntity, AbstractVillager, MerchantRecipe[]> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprMerchantRecipes.class, MerchantRecipe[].class, "[merchant] recipes", "livingentities");
	}

	@Override
	@Nullable
	protected MerchantRecipe[] grab(AbstractVillager villager) {
		return ((Merchant)villager).getRecipes().stream().toArray(size -> new MerchantRecipe[size]);
	}

	@Override
	protected String getPropertyName() {
		return "merchant recipes";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(MerchantRecipe.class, MerchantRecipe[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null || delta[0] == null)
			return;
		switch (mode) {
			case SET:
				for (AbstractVillager villager : getEntities(event))
					((Merchant)villager).getRecipes().clear();
			case ADD:
				for (MerchantRecipe recipe : (MerchantRecipe[]) delta) {
					for (AbstractVillager villager : getEntities(event)) {
						((Merchant)villager).getRecipes().add(recipe);
					}
				}
				break;
			case RESET:
			case DELETE:
				for (AbstractVillager villager : getEntities(event))
					((Merchant)villager).getRecipes().clear();
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (MerchantRecipe recipe : (MerchantRecipe[]) delta) {
					for (AbstractVillager villager : getEntities(event)) {
						((Merchant)villager).getRecipes().remove(recipe);
					}
				}
				break;
			default:
				break;
		}
	}

}
