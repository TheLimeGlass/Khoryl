package me.limeglass.khoryl.elements.entity.frog;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Frog;
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

@Name("Frog Tongue Target")
@Description("Get the tongue target of a frog.")
@Since("1.0.5")
public class ExprTongueTarget extends EntityPropertyExpression<LivingEntity, Frog, Entity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(ExprTongueTarget.class, Entity.class, "[frog] tongue target", "livingentities");
	}

	@Override
	@Nullable
	protected Entity grab(Frog frog) {
		return frog.getTongueTarget();
	}

	@Override
	protected String getPropertyName() {
		return "tongue target";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Entity.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Entity target = (Entity) delta[0];
		for (Frog frog : getEntities(event))
			frog.setTongueTarget(target);
	}

}
