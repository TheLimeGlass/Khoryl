package me.limeglass.khoryl.elements.entity.merchant;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Merchant;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Merchant Trading")
@Description("Check if the villager is currently trading with someone.")
@Since("1.0.0")
public class CondTrading extends EntityPropertyCondition<LivingEntity, AbstractVillager> {

	static {
		register(CondTrading.class, PropertyType.BE, "trading", "livingentities");
	}

	@Override
	protected boolean checkEntity(AbstractVillager villager) {
		return ((Merchant)villager).isTrading();
	}

	@Override
	protected String getPropertyName() {
		return "trading";
	}

}
