package me.limeglass.khoryl.elements.entity.bee;

import org.bukkit.entity.Bee;
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

@Name("Bee Nectar")
@Description("Gets if the bee(s) have nectar. Can be set.")
@Since("1.0.2")
public class ExprBeeNectar extends EntityPropertyExpression<LivingEntity, Bee, Boolean> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 15)))
			register(ExprBeeNectar.class, Boolean.class, "[(has|have)] nectar", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "has nectar";
	}

	@Override
	@Nullable
	protected Boolean grab(Bee bee) {
		return bee.hasNectar();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Boolean nectar = (Boolean) delta[0];
		for (Bee bee : getEntities(event))
			bee.setHasNectar(nectar);
	}

}
