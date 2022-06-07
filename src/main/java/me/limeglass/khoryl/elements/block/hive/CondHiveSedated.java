package me.limeglass.khoryl.elements.block.hive;

import org.bukkit.block.Beehive;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyCondition;

@Name("Beehive Is Sedated")
@Description("Check if the beehives(s) are sedated from a nearby campfire.")
@Since("1.0.3")
public class CondHiveSedated extends BlockStatePropertyCondition<Beehive> {

	static {
		if (Skript.methodExists(Beehive.class, "isSedated"))
			register(CondHiveSedated.class, PropertyType.BE, "sedated", "blocks");
	}

	@Override
	protected boolean checkBlockState(Beehive beehive) {
		return beehive.isSedated();
	}

	@Override
	protected String getPropertyName() {
		return "sedated";
	}

}
