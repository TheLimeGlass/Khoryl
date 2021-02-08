package me.limeglass.khoryl.elements.block.beacon;

import java.util.Collection;

import org.bukkit.block.Beacon;
import org.bukkit.entity.LivingEntity;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import me.limeglass.khoryl.lang.BlockStatePropertyExpression;

@Name("Beacon Affected Enitites")
@Description("Get all the enities in range of a beacon.")
@Since("1.0.3")
public class ExprBeaconAffectedEntities extends BlockStatePropertyExpression<Beacon, LivingEntity[]> {

	static {
		register(ExprBeaconAffectedEntities.class, LivingEntity[].class, "(affected entities around|entities in range of) beacon[s] ", "blocks");
	}

	@Override
	@Nullable
	protected LivingEntity[] grab(Beacon beacon) {
		Collection<LivingEntity> entities = beacon.getEntitiesInRange();
		return entities.toArray(new LivingEntity[entities.size()]);
	}

	@Override
	protected String getPropertyName() {
		return "affected entities around beacon";
	}

}
