package me.limeglass.khoryl.elements.block.sculkshrieker;

import org.bukkit.block.data.type.SculkShrieker;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.BlockDataSetEffect;

@Name("Sculk Shrieker Can Summon")
@Description("Get the can summon property of a sculk shrieker.")
@Since("1.0.5")
public class SetCanSummon extends BlockDataSetEffect<SculkShrieker> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 19)))
			register(SetCanSummon.class, "[sculk [shrieker]] can summon");
	}

	@Override
	protected void execute(SculkShrieker shrieker, boolean value) {
		shrieker.setCanSummon(value);
	}

	@Override
	protected String getPropertyName() {
		return "sculk shrieker can summon";
	}

}
