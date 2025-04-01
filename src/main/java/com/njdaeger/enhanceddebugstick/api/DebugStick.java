package com.njdaeger.enhanceddebugstick.api;

import com.njdaeger.enhanceddebugstick.api.config.ConfigKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class DebugStick extends ItemStack {

    /**
     * Represents a debug stick ItemStack
     */
    public DebugStick(ConfigKey config) {
        super(config.STICK_MATERIAL);
        ItemMeta meta = getItemMeta();
        if (meta == null) throw new IllegalStateException("ItemMeta was null. Please contact the developer.");
        meta.displayName(Component.text("Enhanced Debug Stick", NamedTextColor.BLUE, TextDecoration.BOLD));
        meta.addEnchant(Enchantment.UNBREAKING, 1, true);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        setItemMeta(meta);
        setAmount(1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof ItemStack) {
            ItemStack stack = (ItemStack)obj;
            ItemMeta objMeta = stack.getItemMeta();
            ItemMeta meta = getItemMeta();
            //noinspection DataFlowIssue
            return stack.getType() == getType() &&
                    objMeta != null &&
                    meta != null &&
                    objMeta.hasCustomName() &&
                    ((TextComponent)objMeta.customName()).content().toLowerCase().equals("enhanced debug stick") &&
                    objMeta.hasEnchant(Enchantment.UNBREAKING) &&
                    objMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE) &&
                    objMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) &&
                    objMeta.isUnbreakable();
        }
        return false;
    }
}
