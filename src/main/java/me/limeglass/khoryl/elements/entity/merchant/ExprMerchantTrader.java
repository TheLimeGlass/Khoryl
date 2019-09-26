package me.limeglass.khoryl.elements.entity.merchant;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Merchant;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Merchant Trader")
@Description("Gets the player a villager is trading with. Returns nothing if they're not trading.")
@Since("1.0.0")
public class ExprMerchantTrader extends EntityPropertyExpression<LivingEntity, AbstractVillager, Player> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 14)))
			register(ExprMerchantTrader.class, Player.class, "[merchant] trader", "livingentities");
	}

	@Override
	@Nullable
	protected Player grab(AbstractVillager villager) {
		Merchant merchant = (Merchant)villager;
		if (!merchant.isTrading())
			return null;
		return (Player) merchant.getTrader();
	}

	@Override
	protected String getPropertyName() {
		return "merchant trader";
	}

}
