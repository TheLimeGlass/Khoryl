package me.limeglass.khoryl.lang;

import org.bukkit.inventory.meta.ItemMeta;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.log.ErrorQuality;
import me.limeglass.khoryl.Khoryl;

public abstract class ItemMetaPropertyCondition<S extends ItemMeta> extends PropertyCondition<ItemType> implements ItemMetaSyntax<S> {

	private final boolean printErrors;

	public ItemMetaPropertyCondition() {
		printErrors = Khoryl.getInstance().canRuntimeError();
	}

	public static void register(Class<? extends ItemMetaPropertyCondition<?>> condition, String property) {
		register(condition, property, "itemtypes");
	}

	protected abstract boolean checkBlockData(S meta);

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(ItemType item) {
		ItemMeta meta = item.getItemMeta();
		if (!accepts(meta)) {
			if (printErrors)
				Skript.error("An item meta was not of type " + getCastingBlockDataClass().getName()
						+ " in property expression '" + getPropertyName() + "'", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		return checkBlockData((S)meta);
	}

}
