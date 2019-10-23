package me.limeglass.khoryl.elements.entity.wolf;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Wolf Angry")
@Description("Get if a wolfie is angry.")
@Since("1.0.0")
public class ExprAngry extends EntityPropertyExpression<LivingEntity, Wolf, Boolean> {

	static {
		register(ExprAngry.class, Boolean.class, "angry [state]", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "angry";
	}

	@Override
	@Nullable
	protected Boolean grab(Wolf wolf) {
		return wolf.isAngry();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.RESET || mode == ChangeMode.SET || mode == ChangeMode.DELETE)
			return CollectionUtils.array(boolean.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		boolean angry = (boolean) delta[0];
		for (Wolf wolf : getEntities(event))
			wolf.setAngry(mode == ChangeMode.SET ? angry : false);
	}

}
