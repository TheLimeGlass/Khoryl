package me.limeglass.khoryl.elements;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.inventory.Inventory;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;

public class DefaultConverters {

	static {
		Converters.registerConverter(AbstractVillager.class, Inventory.class, new Converter<AbstractVillager, Inventory>() {
			@Override
			@Nullable
			public Inventory convert(AbstractVillager villager) {
				return villager.getInventory();
			}
		});
	}
}
