package me.limeglass.khoryl.elements.entity.tropicalfish;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.TropicalFish.Pattern;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Tropical Fish Pattern")
@Description("Grab the pattern of tropical fishes.")
@Since("1.0.0")
public class ExprPattern extends EntityPropertyExpression<LivingEntity, TropicalFish, Pattern> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			register(ExprPattern.class, Pattern.class, "[tropical] fish pattern", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "fish pattern";
	}

	@Override
	@Nullable
	protected Pattern grab(TropicalFish fish) {
		return fish.getPattern();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Pattern.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Pattern pattern = (Pattern) delta[0];
		for (TropicalFish fish : getEntities(event))
			fish.setPattern(pattern);
	}

}
