package me.limeglass.khoryl.elements.entity.merchant;

import java.util.Arrays;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.Merchant;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.limeglass.khoryl.lang.EntitySyntax;

@Name("Merchant From Entity")
@Description("Gets the merchant version of an entity, assuming it's a merchant. Useful for getting their inventory.")
@Since("1.0.3")
public class ExprMerchantFromEntity extends SimpleExpression<Merchant> implements EntitySyntax<AbstractVillager> {

	static {
		Skript.registerExpression(ExprMerchantFromEntity.class, Merchant.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] merchant[s] (from|of) %livingentities%", "[(all [[of] the]|the)] %livingentities%'[s] merchant[s]");
	}

	private Expression<LivingEntity> villagers;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Merchant> getReturnType() {
		return Merchant.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		villagers = (Expression<LivingEntity>) exprs[0];
		return true;
	}

	@Override
	@Nullable
	protected Merchant[] get(Event event) {
		return Arrays.stream(villagers.getArray(event))
				.filter(entity -> entity != null)
				.filter(entity -> accepts(entity))
				.map(villager -> ((Merchant)villager))
				.toArray(size -> new Merchant[size]);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "merchant from entities: " + villagers.toString(event, debug);
	}

}
