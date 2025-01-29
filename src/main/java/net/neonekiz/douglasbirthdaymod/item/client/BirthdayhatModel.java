package net.neonekiz.douglasbirthdaymod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.neonekiz.douglasbirthdaymod.DouglasBirthDayMod;
import net.neonekiz.douglasbirthdaymod.item.custom.BirthdayhatItem;
import software.bernie.geckolib.model.GeoModel;

public class BirthdayhatModel extends GeoModel<BirthdayhatItem> {
    @Override
    public ResourceLocation getModelResource(BirthdayhatItem animatable) {
        return new ResourceLocation(DouglasBirthDayMod.MOD_ID, "geo/birthdayhat-geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BirthdayhatItem animatable) {
        return new ResourceLocation(DouglasBirthDayMod.MOD_ID, "textures/armor/birthdayhat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BirthdayhatItem animatable) {
        return new ResourceLocation(DouglasBirthDayMod.MOD_ID, "animations/birthdayhat.animation.json");
    }
}
