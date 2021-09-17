package me.limeglass.khoryl.elements.entity.slime;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Slime size")
@Description("Gets the size of a slime.")
@Since("1.0.4")
public class ExprSlimeSize extends EntityPropertyExpression<LivingEntity, Slime, Integer> {

	static {
		register(ExprSlimeSize.class, Integer.class, "slime size", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "slime size";
	}

	@Override
	@Nullable
	protected Integer grab(Slime slime) {
		return slime.getSize();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Number.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int size = ((Number) delta[0]).intValue();
		for (Slime slime : getEntities(event))
			slime.setSize(size);
	}

}
