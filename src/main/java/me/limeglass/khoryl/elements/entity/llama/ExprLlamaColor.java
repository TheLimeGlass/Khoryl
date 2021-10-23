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

@Name("Llama Color")
@Description("Gets the color of a llama.")
@Since("1.0.4")
public class ExprLlamaColor extends EntityPropertyExpression<LivingEntity, Llama, Llama.Color> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			register(ExprLlamaColor.class, Llama.Color.class, "llama colo[u]r", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "llama colour";
	}

	@Override
	protected Llama.Color grab(Llama llama) {
		return llama.getColor();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Llama.Color.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		Llama.Color color = (Llama.Color) delta[0];
		for (Llama llama : getEntities(event))
			llama.setColor(color);
	}

}
