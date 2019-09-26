package me.limeglass.khoryl.elements;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.stream.Collectors;

import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Streams;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.Version;
import ch.njol.yggdrasil.Fields;
import me.limeglass.khoryl.lang.EnumClassInfo;

public class Types {

	static {
		EnumClassInfo.create(Villager.Profession.class, "villagerprofession").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			EnumClassInfo.create(Villager.Type.class, "villagertype").register();

		Classes.registerClass(new ClassInfo<>(MerchantRecipe.class, "merchantrecipe")
				.user("merchant ?recip(e|ies)")
				.name("Merchant Recipe")
				.description("A configuration of effects that defines the firework when exploded.")
				.defaultExpression(new EventValueExpression<>(MerchantRecipe.class))
				.parser(new Parser<MerchantRecipe>() {

					@Override
					@Nullable
					public MerchantRecipe parse(String input, ParseContext context) {
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(MerchantRecipe recipe, int flags) {
						return "Merchant recipe " + recipe.toString();
					}

					@Override
					public String toVariableNameString(MerchantRecipe recipe) {
						return "Merchant recipe " + ItemType.toString(recipe.getResult());
					}

					@Override
					public String getVariableNamePattern() {
						return "\\S+";
					}

				}).serializer(new Serializer<MerchantRecipe>() {

					@Override
					public Fields serialize(MerchantRecipe recipe) throws NotSerializableException {
						Fields fields = new Fields();
						fields.putPrimitive("experience", recipe.getVillagerExperience());
						fields.putPrimitive("multiplier", recipe.getPriceMultiplier());
						fields.putPrimitive("reward", recipe.hasExperienceReward());
						fields.putPrimitive("max-uses", recipe.getMaxUses());
						fields.putPrimitive("uses", recipe.getUses());
						fields.putObject("result", recipe.getResult());
						Fields ingredients = new Fields();
						int spot = 0;
						for (ItemStack ingredient : recipe.getIngredients()) {
							ingredients.putObject(spot + "", ingredient);
							spot++;
						}
						ingredients.putObject("ingredients", ingredients);
						return fields;
					}

					@Override
					public void deserialize(MerchantRecipe recipe, Fields fields) {
						assert false;
					}

					@Override
					public MerchantRecipe deserialize(Fields fields) throws StreamCorruptedException {
						ItemStack result = fields.getAndRemoveObject("result", ItemStack.class);
						int max = fields.getAndRemovePrimitive("max-uses", int.class);
						MerchantRecipe recipe = new MerchantRecipe(result, max);
						recipe.setVillagerExperience(fields.getAndRemovePrimitive("experience", int.class));
						recipe.setPriceMultiplier(fields.getAndRemovePrimitive("multiplier", float.class));
						recipe.setExperienceReward(fields.getAndRemovePrimitive("reward", boolean.class));
						recipe.setUses(fields.getAndRemovePrimitive("uses", int.class));
						Fields ingredients = fields.getAndRemoveObject("ingredients", Fields.class);
						recipe.setIngredients(Streams.stream(ingredients.iterator())
								.map(context -> {
									try {
										return context.getObject(ItemStack.class);
									} catch (StreamCorruptedException e) {
										e.printStackTrace();
										return null;
									}
								})
								.filter(value -> value != null)
								.collect(Collectors.toList()));
						return recipe;
					}

					@Override
					public boolean mustSyncDeserialization() {
						return false;
					}

					@Override
					protected boolean canBeInstantiated() {
						return false;
					}

				}));
	}

}
