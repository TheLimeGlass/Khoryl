package me.limeglass.khoryl.elements.entity.frog;

import org.bukkit.entity.Frog;
import org.bukkit.entity.Frog.Variant;
import org.bukkit.entity.LivingEntity;
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

@Name("Frog Variant")
@Description("Get the variant of a frog.")
@Since("1.0.5")
public class ExprVariant extends EntityPropertyExpression<LivingEntity, Frog, Variant> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(ExprVariant.class, Variant.class, "frog variant", "livingentities");
	}

	@Override
	@Nullable
	protected Variant grab(Frog frog) {
		return frog.getVariant();
	}

	@Override
	protected String getPropertyName() {
		return "frog variant";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Variant.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Variant variant = (Variant) delta[0];
		for (Frog frog : getEntities(event))
			frog.setVariant(variant);
	}

}
