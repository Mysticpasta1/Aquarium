package com.mystic.aquarium;

import com.mystic.aquarium.block.BlockInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Aquarium implements ModInitializer {
	@Override
	public void onInitialize() {
		BlockInit.init();
	}

	public static Identifier id(String name) {
		return new Identifier("aquarium", name);
	}
}
