package me.limeglass.khoryl.elements.effects;

import org.bukkit.entity.LivingEntity;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("AI")
@Description("Returns whether an entity has AI.")
@Examples("set artificial intelligence of target entity to false")
@Since("1.0.0")
public class SetAI extends EntitySetEffect<LivingEntity> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			register(SetAI.class, "[have] (ai|artificial intelligence)");
	}

	@Override
	public void apply(LivingEntity entity, boolean ai) {
		entity.setAI(ai);
	}

	@Override
	protected String getPropertyName() {
		return "artificial intelligence";
	}

}
