package me.limeglass.khoryl.elements.effects;

import java.util.function.BiConsumer;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.SetEffect;

@Name("Pickup Items")
@Description("Returns whether an entity can pickup items.")
@Examples("set pickup items status of all entities to false")
@Since("1.0.2")
public class SetPickupItems extends SetEffect<LivingEntity> {

	static {
		register(SetPickupItems.class, "pickup items [status]", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "pickup items status";
	}

	@Override
	protected BiConsumer<LivingEntity, Boolean> apply() {
		return (entity, value) -> entity.setCanPickupItems(value);
	}

}
