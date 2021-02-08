package me.limeglass.khoryl.elements.block.beacon;

import org.bukkit.block.Beacon;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Beacon Tier")
@Description("Get the tier of a beacon.")
@Since("1.0.3")
public class ExprBeaconTier extends BlockStatePropertyExpression<Beacon, Number> {

	static {
		register(ExprBeaconTier.class, Number.class, "[beacon] tier[s] ", "blocks");
	}

	@Override
	@Nullable
	protected Number grab(Beacon beacon) {
		return beacon.getTier();
	}

	@Override
	protected String getPropertyName() {
		return "beacon tier";
	}

}
