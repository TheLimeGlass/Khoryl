package me.limeglass.khoryl.elements.block.banner;

import org.bukkit.block.Banner;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockPropertyExpression;

public class ExprBannerBaseColour extends BlockPropertyExpression<Banner, Color> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 8)))
			register(ExprBannerBaseColour.class, Color.class, "[banner] base colo[u]r", "blocks");
	}

	@Override
	@Nullable
	protected Color grab(Banner banner) {
		return SkriptColor.fromDyeColor(banner.getBaseColor());
	}

	@Override
	protected String getPropertyName() {
		return "base colour";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(SkriptColor.class);
		return null;
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		SkriptColor colour = (SkriptColor) delta[0];
		for (Banner banner : getBlockStates(event))
			banner.setBaseColor(colour.asDyeColor());
	}

}
