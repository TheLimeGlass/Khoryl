package me.limeglass.khoryl.elements.block.banner;

import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

@Name("Modify Banner Pattern")
@Description("set/remove the position of a banner pattern.")
@Since("1.0.3")
public class EffModifyBannerPattern extends Effect {

	static {
		Skript.registerEffect(EffModifyBannerPattern.class, "set [banner] pattern at %number% to %bannerpattern% in [banner[s]] %blocks%", "remove [banner] pattern at %number% in [banner[s]] %blocks%");
	}

	private Expression<Pattern> pattern;
	private Expression<Block> banners;
	private Expression<Number> index;
	private boolean set;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		index = (Expression<Number>) exprs[0];
		if (matchedPattern == 1) {
			banners = (Expression<Block>) exprs[1];
			return true;
		}
		set = true;
		pattern = (Expression<Pattern>) exprs[1];
		banners = (Expression<Block>) exprs[2];
		return true;
	}

	@Override
	protected void execute(Event event) {
		Block[] blocks = banners.getArray(event);
		if (blocks == null)
			return;
		Number index = this.index.getSingle(event);
		if (index == null)
			return;
		Pattern pattern = this.pattern.getSingle(event);
		if (pattern == null)
			return;
		for (Block block : blocks) {
			BlockState state = block.getState();
			if (!(state instanceof Banner))
				continue;
			Banner banner = (Banner) state;
			if (set)
				banner.setPattern(index.intValue(), pattern);
			else
				banner.removePattern(index.intValue());
		}
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null)
			return "modify banner pattern";
		return set ? "set " : "remove " + " banner pattern " + pattern.toString(event, debug) + " for banners " + banners.toString(event, debug);
	}

}
