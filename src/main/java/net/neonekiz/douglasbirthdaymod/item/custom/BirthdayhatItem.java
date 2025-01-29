package net.neonekiz.douglasbirthdaymod.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.neonekiz.douglasbirthdaymod.item.client.BirthdayhatRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;
import java.util.function.Consumer;

public class BirthdayhatItem extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


    public BirthdayhatItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BirthdayhatRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                                   EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new BirthdayhatRenderer();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }



    @Override
    public void onArmorTick(ItemStack stack, net.minecraft.world.level.Level level, Player player) {
        super.onArmorTick(stack, level, player);

        // Check if the helmet is equipped in the head slot
        if (player.getItemBySlot(EquipmentSlot.HEAD).equals(stack)) {
            // Only spawn particles on the client side
            if (level.isClientSide) {
                for (int i = 0; i < 1; i++) {
                    // Slightly above the player's head with random offsets
                    double offsetX = (level.random.nextDouble() - 0.2) * 0.3;
                    double offsetY = level.random.nextDouble() + 2.0;
                    double offsetZ = (level.random.nextDouble() - 0.2) * 0.5;

                    // Spawn totem particles with random motion
                    level.addParticle(
                            ParticleTypes.TOTEM_OF_UNDYING,
                            player.getX() + offsetX,
                            player.getY() + offsetY,
                            player.getZ() + offsetZ,
                            (level.random.nextDouble() - 0.2) * 0.1, // Random X motion
                            (level.random.nextDouble() - 0.1) * 0.1, // Random Y motion
                            (level.random.nextDouble() - 0.2) * 0.1  // Random Z motion
                    );
                }

                // Optional: Combine a secondary layer of totem particles for a denser effect
                for (int i = 0; i < 1; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
                    double offsetY = level.random.nextDouble() + 2.2; // Slightly higher
                    double offsetZ = (level.random.nextDouble() - 0.5) * 1.0;

                    level.addParticle(
                            ParticleTypes.TOTEM_OF_UNDYING,
                            player.getX() + offsetX,
                            player.getY() + offsetY,
                            player.getZ() + offsetZ,
                            0, 0.2, 0 // Subtle upward motion
                    );


                }
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        // Get the translated text from the lang file
        String translatedText = Component.translatable("tooltip.douglasbirthdaymod.birthdayhat.tooltip").getString();

        // Define rainbow colors
        int[] rainbowColors = {0xFF0000, 0xFF7F00, 0xFFFF00, 0x00FF00, 0x0000FF, 0x4B0082, 0x8B00FF}; // Rainbow colors

        // Add each character of the translated text with rainbow colors
        for (int i = 0; i < translatedText.length(); i++) {
            char letter = translatedText.charAt(i);
            int color = rainbowColors[i % rainbowColors.length]; // Cycle through rainbow colors
            pTooltipComponents.add(Component.literal(String.valueOf(letter))
                    .setStyle(Style.EMPTY.withColor(color))); // Set the color for each letter
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    }




