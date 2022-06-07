package me.limeglass.khoryl.elements.entity.tadpole;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tadpole;
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

@Name("Tadpole Age")
@Description("Get the age of tadpole.")
@Since("1.0.5")
public class ExprAge extends EntityPropertyExpression<LivingEntity, Tadpole, Integer> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(ExprAge.class, Integer.class, "tad[ ]pole age", "entities");
	}

	@Override
	@Nullable
	protected Integer grab(Tadpole tadpole) {
		return tadpole.getAge();
	}

	@Override
	protected String getPropertyName() {
		return "tad pole age";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(Number.class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		int age = (int) delta[0];
		switch (mode) {
			case ADD:
				for (Tadpole tadpole : getEntities(event)) {
					int exsting = grab(tadpole);
					tadpole.setAge(exsting + age);
				}
				break;
			case RESET:
			case DELETE:
				for (Tadpole tadpole : getEntities(event))
					tadpole.setAge(1);
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (Tadpole tadpole : getEntities(event)) {
					int exsting = grab(tadpole);
					tadpole.setAge(exsting - age);
				}
				break;
			case SET:
				for (Tadpole tadpole : getEntities(event))
					tadpole.setAge(age);
				break;
			default:
				break;
		}
	}

}
