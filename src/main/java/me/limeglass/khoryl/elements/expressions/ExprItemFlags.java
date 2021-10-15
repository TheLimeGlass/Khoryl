package me.limeglass.khoryl.elements.expressions;

import java.util.Arrays;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprItemFlags extends PropertyExpression<ItemType, ItemFlag> {

	static {
		register(ExprItemFlags.class, ItemFlag.class, "item flags", "itemtypes");
	}

	@Override
	public Class<? extends ItemFlag> getReturnType() {
		return ItemFlag.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends ItemType>) exprs[0]);
		return true;
	}

	@Override
	protected ItemFlag[] get(Event event, ItemType[] items) {
		return Arrays.stream(items).flatMap(item -> item.getItemMeta().getItemFlags().stream()).toArray(ItemFlag[]::new);
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "the item flags of " + getExpr().toString(event, debug);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(ItemFlag[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null)
			return;
		ItemFlag[] flags = (ItemFlag[]) delta;
		switch (mode) {
			case ADD:
				for (ItemType item : getExpr().getArray(event)) {
					ItemMeta meta = item.getItemMeta();
					meta.addItemFlags(flags);
					item.setItemMeta(meta);
				}
				break;
			case RESET:
			case DELETE:
				for (ItemType item : getExpr().getArray(event)) {
					ItemMeta meta = item.getItemMeta();
					meta.getItemFlags().clear();
					item.setItemMeta(meta);
				}
				break;
			case REMOVE:
			case REMOVE_ALL:
				for (ItemType item : getExpr().getArray(event)) {
					ItemMeta meta = item.getItemMeta();
					meta.removeItemFlags(flags);
					item.setItemMeta(meta);
				}
				break;
			case SET:
				for (ItemType item : getExpr().getArray(event)) {
					ItemMeta meta = item.getItemMeta();
					meta.getItemFlags().clear();
					meta.addItemFlags(flags);
					item.setItemMeta(meta);
				}
				break;
			default:
				break;
		}
	}

}
