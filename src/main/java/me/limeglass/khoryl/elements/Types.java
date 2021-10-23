package me.limeglass.khoryl.elements;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.Locale;
import java.util.stream.Collectors;

import org.bukkit.DyeColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.block.data.type.BigDripleaf.Tilt;
import org.bukkit.block.data.type.PointedDripstone.Thickness;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Llama;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;
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
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 8)))
			EnumClassInfo.create(PatternType.class, "bannerpatterntype").register();
		EnumClassInfo.create(Villager.Profession.class, "villagerprofession").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			EnumClassInfo.create(Villager.Type.class, "villagertype").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 13)))
			EnumClassInfo.create(TropicalFish.Pattern.class, "tropicalfishpattern").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			EnumClassInfo.create(Tilt.class, "bigleaftilt").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			EnumClassInfo.create(Thickness.class, "dripstonethickness").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 11)))
			EnumClassInfo.create(Llama.Color.class, "llamacolor").register();
		EnumClassInfo.create(ItemFlag.class, "itemflag").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 17)))
			EnumClassInfo.create(ArmorStand.LockType.class, "locktype").register();
		if (!Skript.getMinecraftVersion().isSmallerThan(new Version(1, 15)))
			EnumClassInfo.create(EquipmentSlot.class, "equipmentslot").register();

		if (Classes.getExactClassInfo(BlockState.class) != null)
			Classes.registerClass(new ClassInfo<>(BlockState.class, "blockstate")
					.user("block ?states?")
					.name("Block State")
					.defaultExpression(new EventValueExpression<>(BlockState.class))
					.parser(new Parser<BlockState>() {
	
						@Override
						@Nullable
						public BlockState parse(String input, ParseContext context) {
							return null;
						}
	
						@Override
						public boolean canParse(ParseContext context) {
							return false;
						}
	
						@Override
						public String toString(BlockState state, int flags) {
							return toVariableNameString(state);
						}
	
						@Override
						public String toVariableNameString(BlockState state) {
							return "Block state " + state.toString();
						}
	
						@Override
						public String getVariableNamePattern() {
							return "\\S+";
						}
	
					}));

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

		Classes.registerClass(new ClassInfo<>(Pattern.class, "bannerpattern")
				.user("banner patterns?")
				.name("Banner Pattern")
				.defaultExpression(new EventValueExpression<>(Pattern.class))
				.parser(new Parser<Pattern>() {

					@Override
					@Nullable
					public Pattern parse(String input, ParseContext context) {
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(Pattern pattern, int flags) {
						return "Banner pattern " + pattern.toString();
					}

					@Override
					public String toVariableNameString(Pattern pattern) {
						return "Pattern pattern " + pattern.getPattern().name().toLowerCase(Locale.US) + " colored " + pattern.getColor().name().toLowerCase(Locale.US);
					}

					@Override
					public String getVariableNamePattern() {
						return "\\S+";
					}

				}).serializer(new Serializer<Pattern>() {

					@Override
					public Fields serialize(Pattern pattern) throws NotSerializableException {
						Fields fields = new Fields();
						fields.putPrimitive("type", pattern.getPattern().name());
						fields.putPrimitive("color", pattern.getColor().name());
						return fields;
					}

					@Override
					public void deserialize(Pattern pattern, Fields fields) {
						assert false;
					}

					@Override
					public Pattern deserialize(Fields fields) throws StreamCorruptedException {
						DyeColor color = DyeColor.valueOf(fields.getAndRemovePrimitive("color", String.class));
						PatternType type = PatternType.valueOf(fields.getAndRemovePrimitive("type", String.class));
						return new Pattern(color, type);
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

		if (Classes.getClassInfoNoError("llamainventory") == null)
			Classes.registerClass(new ClassInfo<>(LlamaInventory.class, "llamainventory")
					.user("llamainventory(ies|s)?")
					.name("LLama Inventory")
					.defaultExpression(new EventValueExpression<>(LlamaInventory.class))
					.parser(new Parser<LlamaInventory>() {
	
						@Override
						@Nullable
						public LlamaInventory parse(String input, ParseContext context) {
							return null;
						}
	
						@Override
						public boolean canParse(ParseContext context) {
							return false;
						}
	
						@Override
						public String toString(LlamaInventory inventory, int flags) {
							return "LlamaInventory " + inventory.toString();
						}
	
						@Override
						public String toVariableNameString(LlamaInventory inventory) {
							return "llama inventory";
						}
	
						@Override
						public String getVariableNamePattern() {
							return "\\S+";
						}
	
					}));
	}

}
