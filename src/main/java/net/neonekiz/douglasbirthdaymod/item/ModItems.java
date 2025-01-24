package net.neonekiz.douglasbirthdaymod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.neonekiz.douglasbirthdaymod.DouglasBirthDayMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =

            DeferredRegister.create(ForgeRegistries.ITEMS, DouglasBirthDayMod.MOD_ID);

public static final RegistryObject<Item> PLUSHIE = ITEMS.register("plushie",
        () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);



            }

}
