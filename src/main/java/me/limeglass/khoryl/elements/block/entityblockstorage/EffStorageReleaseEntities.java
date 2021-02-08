package me.limeglass.khoryl.elements.block.entityblockstorage;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.EntityBlockStorage;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Version;
import ch.njol.util.Kleenean;

@Name("Release Entities")
@Description("Release all entities from an Entity Block Storage.")
@Since("1.0.3")
public class EffStorageReleaseEntities extends Effect {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 16)))
			Skript.registerEffect(EffStorageReleaseEntities.class, "release [(all [[of] the]|the)] entities (from|in) [block[s]] %blocks%");
	}

	private Expression<Block> blocks;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		blocks = (Expression<Block>) exprs[0];
		return true;
	}

	@Override
	protected void execute(Event event) {
		for (Block block : blocks.getArray(event)) {
			BlockState state = block.getState();
			if (!(state instanceof EntityBlockStorage))
				continue;
			EntityBlockStorage<?> storage = (EntityBlockStorage<?>) state;
			storage.releaseEntities();
			storage.update();
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null)
			return "release entities";
		return "release entities in " + blocks.toString(event, debug);
	}

}
