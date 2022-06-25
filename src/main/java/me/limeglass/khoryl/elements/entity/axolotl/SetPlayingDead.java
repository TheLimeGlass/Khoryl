package me.limeglass.khoryl.elements.entity.axolotl;

import org.bukkit.entity.Axolotl;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntitySetEffect;

@Name("Axolotl Playing Dead")
@Description("Set the playing dead state of a axolotl.")
@Since("1.0.5")
public class SetPlayingDead extends EntitySetEffect<Axolotl> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			register(SetPlayingDead.class, "play[ing] dead", "livingentities");
	}

	@Override
	public void apply(Axolotl axolotl, boolean value) {
		axolotl.setPlayingDead(value);
	}

	@Override
	protected String getPropertyName() {
		return "playing dead";
	}

}
