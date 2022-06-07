package me.limeglass.khoryl.elements.entity.steerable;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Steerable;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Steerable Item")
@Description("Get the item used to steer a steerable entity such as a pig or strider.")
@Since("1.0.4")
public class ExprSteerMaterial extends EntityPropertyExpression<Entity, Steerable, ItemType> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 16)))
			register(ExprSteerMaterial.class, ItemType.class, "[used] steer[able] (item|material)", "entities");
	}

	@Override
	public Class<? extends ItemType> getReturnType() {
		return ItemType.class;
	}

	@Override
	protected String getPropertyName() {
		return "steer item";
	}

	@Override
	@Nullable
	public ItemType grab(Steerable steerable) {
		return new ItemType(steerable.getSteerMaterial());
	}
}
