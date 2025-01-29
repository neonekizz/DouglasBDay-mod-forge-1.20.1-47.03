package net.neonekiz.douglasbirthdaymod.item.client;

import net.neonekiz.douglasbirthdaymod.item.custom.BirthdayhatItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BirthdayhatRenderer extends GeoArmorRenderer<BirthdayhatItem> {
    public BirthdayhatRenderer() {
        super(new BirthdayhatModel());
    }
}
