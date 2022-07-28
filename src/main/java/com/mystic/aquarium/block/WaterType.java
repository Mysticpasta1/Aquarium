package com.mystic.aquarium.block;

import net.minecraft.util.StringIdentifiable;

public enum WaterType implements StringIdentifiable {
    Fresh,
    Salt;

    @Override
    public String asString() {
        if(this == Fresh) {
            return "fresh";
        } else {
            return "salt";
        }
    }
}
