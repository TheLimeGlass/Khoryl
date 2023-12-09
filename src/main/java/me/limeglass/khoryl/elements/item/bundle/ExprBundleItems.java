package me.limeglass.khoryl.elements.item.bundle;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

@Name("Bundle Items")
@Description("Get or change the items inside a bundle.")
@Since("1.0.4")
public class ExprBundleItems extends PropertyExpression<ItemStack, ItemStack> {

	static {
		register(ExprBundleItems.class, ItemStack.class, "bundle item[s]", "itemstacks");
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends ItemStack>) exprs[0]);
		return true;
	}

	@Override
	protected ItemStack[] get(Event event, ItemStack[] source) {
		return Arrays.stream(source)
				.map(ItemStack::getItemMeta)
				.filter(BundleMeta.class::isInstance)
				.map(BundleMeta.class::cast)
				.map(BundleMeta::getItems)
				.flatMap(List::stream)
				.toArray(ItemStack[]::new);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return new Class<?>[] {ItemStack[].class};
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		List<ItemStack> items = delta == null ? null : Lists.newArrayList((ItemStack[]) delta);
		switch (mode) {
			case ADD:
				for (ItemStack itemstack : getExpr().getArray(event)) {
					ItemMeta itemMeta = itemstack.getItemMeta();
					if (!(itemMeta instanceof BundleMeta))
						continue;
					BundleMeta bundle = (BundleMeta) itemMeta;
					items.forEach(item -> bundle.addItem(item));
					itemstack.setItemMeta(bundle);
				}
				break;
			case RESET:
			case DELETE:
				for (ItemStack itemstack : getExpr().getArray(event)) {
					ItemMeta itemMeta = itemstack.getItemMeta();
					if (!(itemMeta instanceof BundleMeta))
						continue;
					BundleMeta bundle = (BundleMeta) itemMeta;
					bundle.setItems(null);
					itemstack.setItemMeta(bundle);
				}
				break;
			case REMOVE:
			case REMOVE_ALL:
				for (ItemStack itemstack : getExpr().getArray(event)) {
					ItemMeta itemMeta = itemstack.getItemMeta();
					if (!(itemMeta instanceof BundleMeta))
						continue;
					BundleMeta bundle = (BundleMeta) itemMeta;
					List<ItemStack> list = Lists.newArrayList(bundle.getItems());
					list.removeIf(item -> items.stream().anyMatch(item::isSimilar));
					bundle.setItems(list);
					itemstack.setItemMeta(bundle);
				}
				break;
			case SET:
				for (ItemStack itemstack : getExpr().getArray(event)) {
					ItemMeta itemMeta = itemstack.getItemMeta();
					if (!(itemMeta instanceof BundleMeta))
						continue;
					BundleMeta bundle = (BundleMeta) itemMeta;
					bundle.setItems(items);
					itemstack.setItemMeta(bundle);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "bundle items of " + getExpr().toString(event, debug);
	}

}
