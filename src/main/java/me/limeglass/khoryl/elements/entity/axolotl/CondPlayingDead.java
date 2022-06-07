package me.limeglass.khoryl.elements.entity.axolotl;

import org.bukkit.entity.Axolotl;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyCondition;

@Name("Axolotl Playing Dead")
@Description("Check if a axolotl is playing dead.")
@Since("1.0.5")
public class CondPlayingDead extends EntityPropertyCondition<Axolotl> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(CondPlayingDead.class, "playing dead");
	}

	@Override
	protected boolean checkEntity(Axolotl axolotl) {
		return axolotl.isPlayingDead();
	}

	@Override
	protected String getPropertyName() {
		return "playing dead";
	}

}
