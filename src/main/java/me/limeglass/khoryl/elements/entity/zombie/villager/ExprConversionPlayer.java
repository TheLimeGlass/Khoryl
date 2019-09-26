package me.limeglass.khoryl.elements.entity.zombie.villager;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Zombie Villager Conversion Player")
@Description("Gets the player who initiated the conversion.")
@Since("1.0.0")
public class ExprConversionPlayer extends EntityPropertyExpression<LivingEntity, ZombieVillager, OfflinePlayer> {

	static {
		register(ExprConversionPlayer.class, OfflinePlayer.class, "(conversion|converting) player", "livingentities");
	}

	@Override
	protected String getPropertyName() {
		return "conversion player";
	}

	@Override
	@Nullable
	protected OfflinePlayer grab(ZombieVillager villager) {
		if (!villager.isConverting())
			return null;
		return villager.getConversionPlayer();
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.DELETE)
			return CollectionUtils.array(OfflinePlayer.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null) {
			Skript.error("Conversion player can't be null."
					+ "If you're trying to reset the conversion player use the reset/delete changer.", ErrorQuality.SEMANTIC_ERROR);
			return;
		}
		OfflinePlayer player = (OfflinePlayer) delta[0];
		for (ZombieVillager villager : getEntities(event))
			villager.setConversionPlayer(mode == ChangeMode.SET ? player : null);
	}

}
