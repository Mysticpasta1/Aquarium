package com.mystic.aquarium.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public  class AquariumDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(new AquariumBlockMultiDataProvider(fabricDataGenerator));
    }

    private static class AquariumBlockMultiDataProvider extends FabricModelProvider {
        public AquariumBlockMultiDataProvider(FabricDataGenerator dataGenerator) {
            super(dataGenerator);
        }
        
        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        }
    }
}