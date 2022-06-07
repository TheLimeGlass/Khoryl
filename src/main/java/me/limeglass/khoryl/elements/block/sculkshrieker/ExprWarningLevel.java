package me.limeglass.khoryl.elements.block.sculkshrieker;

import org.bukkit.block.SculkShrieker;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Sculk Shrieker warning level")
@Description("Get warning level of a Sculk Shrieker block.")
@Since("1.0.5")
public class ExprWarningLevel extends BlockStatePropertyExpression<SculkShrieker, Integer> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(ExprWarningLevel.class, Integer.class, "[sculk [shrieker]] warning level[s]", "blocks");
	}

	@Override
	@Nullable
	protected Integer grab(SculkShrieker shrieker) {
		return shrieker.getWarningLevel();
	}

	@Override
	protected String getPropertyName() {
		return "warning level";
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
		int level = (int) delta[0];
		switch (mode) {
			case ADD:
				for (SculkShrieker shrieker : getBlockStates(event)) {
					int exsting = grab(shrieker);
					shrieker.setWarningLevel(exsting + level);
					shrieker.update();
				}
				break;
			case RESET:
			case DELETE:
				for (SculkShrieker shrieker : getBlockStates(event)) {
					shrieker.setWarningLevel(1);
					shrieker.update();
				}
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (SculkShrieker shrieker : getBlockStates(event)) {
					int exsting = grab(shrieker);
					shrieker.setWarningLevel(exsting - level);
					shrieker.update();
				}
				break;
			case SET:
				for (SculkShrieker shrieker : getBlockStates(event)) {
					shrieker.setWarningLevel(level);
					shrieker.update();
				}
				break;
			default:
				break;
		}
	}

}
