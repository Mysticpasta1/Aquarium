package com.mystic.aquarium.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class AquariumBlock extends Block {
    private WaterType type;

    public AquariumBlock(WaterType type) {
        super(AbstractBlock.Settings.of(Material.GLASS));
        this.type = type;
    }
}
