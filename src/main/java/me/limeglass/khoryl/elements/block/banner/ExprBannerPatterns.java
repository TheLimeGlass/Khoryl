package me.limeglass.khoryl.elements.block.banner;

import java.util.List;

import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.khoryl.lang.BlockPropertyExpression;

@Name("Banner Patterns")
@Description("Get banner patterns on a banner.")
@Since("1.0.3")
public class ExprBannerPatterns extends BlockPropertyExpression<Banner, Pattern[]> {

	static {
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 8)))
			register(ExprBannerPatterns.class, Pattern[].class, "banner patterns", "blocks");
	}

	@Override
	@Nullable
	protected Pattern[] grab(Banner banner) {
		List<Pattern> patterns = banner.getPatterns();
		return patterns.toArray(new Pattern[patterns.size()]);
	}

	@Override
	protected String getPropertyName() {
		return "banner patterns";
	}

	@Nullable
	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(Pattern[].class);
	}

	@Override
	public void change(Event event, @Nullable Object[] delta, ChangeMode mode) {
		if (delta[0] == null)
			return;
		List<Pattern> patterns = Lists.newArrayList((Pattern[]) delta[0]);
		for (Banner banner : getBlockStates(event)) {
			List<Pattern> existing = banner.getPatterns();
			switch (mode) {
				case ADD:
					existing.addAll(patterns);
					break;
				case REMOVE_ALL:
				case REMOVE:
					existing.removeAll(patterns);
					break;
				case DELETE:
				case RESET:
					existing.clear();
					break;
				case SET:
					existing.clear();
					existing.addAll(patterns);
					break;
			}
		}
	}

}
