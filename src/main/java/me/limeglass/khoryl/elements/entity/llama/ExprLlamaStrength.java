package me.limeglass.khoryl.elements.entity.llama;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
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

@Name("Llama Strength")
@Description("Gets the strength of a llama.")
@Since("1.0.4")
public class ExprLlamaStrength extends EntityPropertyExpression<LivingEntity, Llama, Integer> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			register(ExprLlamaStrength.class, Integer.class, "llama strength", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "llama strength";
	}

	@Override
	@Nullable
	protected Integer grab(Llama llama) {
		return llama.getStrength();
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
		int strength = ((Number) delta[0]).intValue();
		for (Llama llama : getEntities(event))
			llama.setStrength(strength);
	}

}
