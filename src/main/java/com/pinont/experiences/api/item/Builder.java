package com.pinont.experiences.api.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Builder extends ItemStack {

    ItemStack itemStack;

    ItemMeta itemMeta;

    public Builder(Material material) {}

    @Override
    public ItemMeta getItemMeta(){

        return null;
    }

}
