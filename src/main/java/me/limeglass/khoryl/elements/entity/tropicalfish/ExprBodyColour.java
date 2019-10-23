package me.limeglass.khoryl.elements.entity.tropicalfish;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TropicalFish;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.SkriptColor;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Tropical Fish Body Colour")
@Description("Grab the body colour of tropical fishes.")
@Since("1.0.0")
public class ExprBodyColour extends EntityPropertyExpression<LivingEntity, TropicalFish, SkriptColor> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(ExprBodyColour.class, SkriptColor.class, "[tropical] fish [body] colo[u]r", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "fish body colour";
	}

	@Override
	@Nullable
	protected SkriptColor grab(TropicalFish fish) {
		return SkriptColor.fromDyeColor(fish.getBodyColor()).orElse(SkriptColor.BLACK);
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(SkriptColor.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		SkriptColor color = (SkriptColor) delta[0];
		for (TropicalFish fish : getEntities(event))
			fish.setBodyColor(color.asDyeColor());
	}

}
