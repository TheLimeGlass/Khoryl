package me.limeglass.khoryl.elements.entity.boss;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Boss;
import org.bukkit.entity.LivingEntity;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Version;
import me.limeglass.khoryl.lang.EntityPropertyExpression;

@Name("Boss BossBar")
@Description("Returns the BossBar of a Boss. Use this syntax in conjunction with Skellett.")
@Since("1.0.0")
public class ExprBossbar extends EntityPropertyExpression<LivingEntity, Boss, BossBar> {

	static {
		if (Bukkit.getPluginManager().getPlugin("Skellett") != null)
			if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 12)))
				register(ExprBossbar.class, BossBar.class, "boss[ ]bar", "livingentities");
	}

	@Override
	@Nullable
	protected BossBar grab(Boss boss) {
		return boss.getBossBar();
	}

	@Override
	protected String getPropertyName() {
		return "bossbar";
	}

}
