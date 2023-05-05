package com.mystic.aquarium;

import com.mystic.aquarium.init.BlockInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("aquarium")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Aquarium {

	public Aquarium() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		BlockInit.init(bus);
	}
}
