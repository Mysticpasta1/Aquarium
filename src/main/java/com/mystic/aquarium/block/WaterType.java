package com.mystic.aquarium.block;

import net.minecraft.util.StringRepresentable;

public enum WaterType implements StringRepresentable {
    Fresh,
    Salt;

    @Override
    public String getSerializedName() {
        if(this == Fresh) {
            return "fresh";
        } else {
            return "salt";
        }
    }
}
