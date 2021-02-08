package me.limeglass.khoryl.elements.block.skull;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.Skull;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Skull Owner")
@Description("Get skull owner of a Skull block.")
@Since("1.0.3")
public class ExprSkullOwner extends BlockStatePropertyExpression<Skull, OfflinePlayer> {

	static {
		register(ExprSkullOwner.class, OfflinePlayer.class, "skull owner", "blocks");
	}

	@Override
	@Nullable
	protected OfflinePlayer grab(Skull skull) {
		return skull.getOwningPlayer();
	}

	@Override
	protected String getPropertyName() {
		return "skull owner";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(OfflinePlayer.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		OfflinePlayer owner = (OfflinePlayer) delta[0];
		for (Skull skull : getBlockStates(event)) {
			skull.setOwningPlayer(owner);
			skull.update();
		}
	}

}
