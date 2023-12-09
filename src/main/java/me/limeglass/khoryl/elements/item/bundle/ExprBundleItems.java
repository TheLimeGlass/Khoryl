package me.limeglass.khoryl.elements.item.bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.ItemMetaExpression;

@Name("Bundle Items")
@Description("Get or change the items inside a bundle. The bundle must start off as an ItemType.")
@Since("1.0.4")
public class ExprBundleItems extends ItemMetaExpression<BundleMeta, ItemStack> {

	static {
		register(ExprBundleItems.class, ItemStack.class, "bundle item[s]");
	}

	@Override
	@Nullable
	protected ItemStack[] grab(BundleMeta bundle) {
		return bundle.getItems().toArray(ItemStack[]::new);
	}

	@Override
	protected String getPropertyName() {
		return "bundle items";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(ItemType[].class, ItemStack[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta == null)
			return;
		List<ItemStack> items = new ArrayList<ItemStack>();
		if (delta instanceof ItemStack[]) {
			items.addAll(Lists.newArrayList((ItemStack[]) delta));
		} else {
			items.addAll(Arrays.stream((ItemType[]) delta).map(ItemType::getRandom).toList());
		}
		switch (mode) {
			case ADD:
				for (Entry<ItemType, BundleMeta> entry : getMetasMap(event).entrySet()) {
					BundleMeta bundle = entry.getValue();
					items.forEach(item -> bundle.addItem(item));
					entry.getKey().setItemMeta(bundle);
				}
				break;
			case RESET:
			case DELETE:
				for (Entry<ItemType, BundleMeta> entry : getMetasMap(event).entrySet()) {
					BundleMeta bundle = entry.getValue();
					bundle.getItems().clear();
					entry.getKey().setItemMeta(bundle);
				}
				break;
			case REMOVE:
			case REMOVE_ALL:
				for (Entry<ItemType, BundleMeta> entry : getMetasMap(event).entrySet()) {
					BundleMeta bundle = entry.getValue();
					bundle.getItems().removeIf(item -> items.stream().anyMatch(item::isSimilar));
					entry.getKey().setItemMeta(bundle);
				}
				break;
			case SET:
				for (Entry<ItemType, BundleMeta> entry : getMetasMap(event).entrySet()) {
					BundleMeta bundle = entry.getValue();
					bundle.setItems(items);
					entry.getKey().setItemMeta(bundle);
				}
				break;
			default:
				break;
		}
	}

}
