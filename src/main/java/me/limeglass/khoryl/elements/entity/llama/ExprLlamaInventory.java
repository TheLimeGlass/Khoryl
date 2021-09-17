package me.limeglass.khoryl.elements.entity.llama;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.LlamaInventory;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Llama Inventory")
@Description("Gets the inventory of a llama.")
@Since("1.0.4")
public class ExprLlamaInventory extends EntityPropertyExpression<LivingEntity, Llama, LlamaInventory> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			register(ExprLlamaInventory.class, LlamaInventory.class, "llama inventory", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "llama inventory";
	}

	@Override
	@Nullable
	protected LlamaInventory grab(Llama llama) {
		return llama.getInventory();
	}

}
