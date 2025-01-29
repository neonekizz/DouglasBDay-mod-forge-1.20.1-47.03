package net.neonekiz.douglasbirthdaymod.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.neonekiz.douglasbirthdaymod.DouglasBirthDayMod;
import net.neonekiz.douglasbirthdaymod.item.custom.BirthdayhatItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =

            DeferredRegister.create(ForgeRegistries.ITEMS, DouglasBirthDayMod.MOD_ID);



    public static final RegistryObject<Item> BIRTHDAY_HELMET = ITEMS.register("birthday_helmet",
            () -> new BirthdayhatItem(ModArmorMaterials.BDAYHAT, ArmorItem.Type.HELMET, new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);



            }

}
