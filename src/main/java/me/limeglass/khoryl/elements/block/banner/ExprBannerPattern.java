package me.limeglass.khoryl.elements.block.banner;

import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Color;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

@Name("Banner Pattern")
@Description("Create a banner pattern.")
@Since("1.0.3")
public class ExprBannerPattern extends SimpleExpression<Pattern> {

	static {
		Skript.registerExpression(ExprBannerPattern.class, Pattern.class, ExpressionType.COMBINED, "%bannerpatterntype% [banner] pattern colo[u]red %color%");
	}

	private Expression<PatternType> type;
	private Expression<Color> color;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		type = (Expression<PatternType>) exprs[0];
		color = (Expression<Color>) exprs[1];
		return true;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Pattern> getReturnType() {
		return Pattern.class;
	}

	@Override
	@Nullable
	protected Pattern[] get(Event event) {
		PatternType type = this.type.getSingle(event);
		if (type == null)
			return null;
		Color color = this.color.getSingle(event);
		if (color == null)
			return null;
		return CollectionUtils.array(new Pattern(color.asDyeColor(), type));
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null)
			return "Banner pattern";
		return "Banner pattern " + type.toString(event, debug) + " with color " + color.toString(event, debug);
	}

}
