package me.limeglass.khoryl.elements.block.endgateway;

import org.bukkit.block.EndGateway;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStateSetEffect;

@Name("Set End Gateway Exact Teleport")
@Description("Set if end gateways will teleport entities to the exact location or a nearby location.")
@Since("1.1.0")
public class SetExactTeleport extends BlockStateSetEffect<EndGateway> {

	static {
		register(SetExactTeleport.class, "exact location [teleport]");
	}

	@Override
	protected void execute(EndGateway gateway, boolean value) {
		gateway.setExactTeleport(value);
	}

	@Override
	protected String getPropertyName() {
		return "exact location";
	}

}
