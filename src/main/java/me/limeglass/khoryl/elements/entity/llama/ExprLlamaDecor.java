package me.limeglass.khoryl.elements.entity.llama;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;

@Name("Llama Inventory Decor")
@Description("Gets the color of a llama.")
@Since("1.0.4")
public class ExprLlamaDecor extends SimplePropertyExpression<LlamaInventory, ItemStack> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			register(ExprLlamaDecor.class, ItemStack.class, "[llama] [inventory] decor [item]", "llamainventories");
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	@Nullable
	public ItemStack convert(LlamaInventory inventory) {
		return inventory.getDecor();
	}

	@Override
	protected String getPropertyName() {
		return "llama decor item";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(ItemStack.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		ItemStack item = (ItemStack) delta[0];
		for (LlamaInventory inventory : getExpr().getArray(event))
			inventory.setDecor(item);
	}

}
