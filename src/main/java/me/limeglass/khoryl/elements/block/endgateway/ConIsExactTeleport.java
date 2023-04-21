package me.limeglass.khoryl.elements.block.endgateway;

import org.bukkit.block.EndGateway;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyCondition;

@Name("End Gateway Is Exact Teleport")
@Description("Check if the gateways will teleport entities directly to the exit location instead of finding a nearby location.")
@Since("1.1.0")
public class ConIsExactTeleport extends BlockStatePropertyCondition<EndGateway> {

	static {
		register(ConIsExactTeleport.class, "exact teleport [location]");
	}

	@Override
	protected boolean checkBlockState(EndGateway gateway) {
		return gateway.isExactTeleport();
	}

	@Override
	protected String getPropertyName() {
		return "exact teleport";
	}

}
