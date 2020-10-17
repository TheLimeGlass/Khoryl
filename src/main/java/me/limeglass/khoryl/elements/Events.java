package me.limeglass.khoryl.elements;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class Events {

	static {
		Skript.registerEvent("villager acquire trade", SimpleEvent.class, VillagerAcquireTradeEvent.class, "villager acquire [new] trade");
		EventValues.registerEventValue(VillagerAcquireTradeEvent.class, AbstractVillager.class, new Getter<AbstractVillager, VillagerAcquireTradeEvent>() {

			@Override
			@Nullable
			public AbstractVillager get(VillagerAcquireTradeEvent event) {
				return event.getEntity();
			}

		}, 0);
		EventValues.registerEventValue(VillagerAcquireTradeEvent.class, MerchantRecipe.class, new Getter<MerchantRecipe, VillagerAcquireTradeEvent>() {

			@Override
			@Nullable
			public MerchantRecipe get(VillagerAcquireTradeEvent event) {
				return event.getRecipe();
			}

		}, 0);
		Skript.registerEvent("villager career change", SimpleEvent.class, VillagerCareerChangeEvent.class, "villager career change");
		EventValues.registerEventValue(VillagerCareerChangeEvent.class, AbstractVillager.class, new Getter<AbstractVillager, VillagerCareerChangeEvent>() {

			@Override
			@Nullable
			public AbstractVillager get(VillagerCareerChangeEvent event) {
				return event.getEntity();
			}

		}, 0);
		EventValues.registerEventValue(VillagerCareerChangeEvent.class, Profession.class, new Getter<Profession, VillagerCareerChangeEvent>() {

			@Override
			@Nullable
			public Profession get(VillagerCareerChangeEvent event) {
				return event.getProfession();
			}

		}, 0);
	}

}
