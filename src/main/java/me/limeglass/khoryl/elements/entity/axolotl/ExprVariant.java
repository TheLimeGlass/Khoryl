package me.limeglass.khoryl.elements.entity.axolotl;

import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Axolotl.Variant;
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

@Name("Axolotl Variant")
@Description("Get the variant of a axolotl.")
@Since("1.0.5")
public class ExprVariant extends EntityPropertyExpression<LivingEntity, Axolotl, Variant> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(ExprVariant.class, Variant.class, "axolotl variant", "livingentities");
	}

	@Override
	@Nullable
	protected Variant grab(Axolotl axolotl) {
		return axolotl.getVariant();
	}

	@Override
	protected String getPropertyName() {
		return "axolotl variant";
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
		for (Axolotl axolotl : getEntities(event))
			axolotl.setVariant(variant);
	}

}
