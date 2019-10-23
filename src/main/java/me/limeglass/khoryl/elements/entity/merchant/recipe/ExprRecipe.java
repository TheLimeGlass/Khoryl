package me.limeglass.khoryl.elements.entity.merchant.recipe;

import org.bukkit.event.Event;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;

public class ExprRecipe extends EventValueExpression<MerchantRecipe> {

	static {
		Skript.registerExpression(ExprRecipe.class, MerchantRecipe.class, ExpressionType.SIMPLE, "[the] merchant recipe");
	}

	@Nullable
	private EventValueExpression<MerchantRecipe> recipe;

	public ExprRecipe() {
		super(MerchantRecipe.class);
	}

	@Override
	@Nullable
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode != ChangeMode.SET)
			return null;
		recipe = new EventValueExpression<>(MerchantRecipe.class);
		if (recipe.init())
			return new Class[] {MerchantRecipe.class};
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		MerchantRecipe newRecipe = (MerchantRecipe) delta[0];
		if (event instanceof VillagerAcquireTradeEvent)
			((VillagerAcquireTradeEvent)event).setRecipe(newRecipe);
	}

}
