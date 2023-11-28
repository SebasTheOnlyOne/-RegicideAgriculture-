package org.regicide.test.SystemOfGrow;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Crops;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import java.util.Arrays;


public class Grow implements Listener
{

    Biome[] veryBadBiomes = {Biome.COLD_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.DESERT, Biome.DEEP_DARK, Biome.DEEP_FROZEN_OCEAN,
            Biome.FROZEN_PEAKS, Biome.FROZEN_OCEAN, Biome.FROZEN_RIVER, Biome.DRIPSTONE_CAVES, Biome.ICE_SPIKES,
            Biome.JAGGED_PEAKS, Biome.BASALT_DELTAS, Biome.CRIMSON_FOREST, Biome.END_BARRENS, Biome.END_HIGHLANDS,
            Biome.END_MIDLANDS, Biome.ERODED_BADLANDS, Biome.NETHER_WASTES, Biome.SMALL_END_ISLANDS, Biome.SOUL_SAND_VALLEY,
            Biome.THE_END, Biome.THE_VOID, Biome.WARPED_FOREST};

    Biome[] badBiomes = {Biome.GROVE, Biome.BADLANDS, Biome.SAVANNA, Biome.BEACH, Biome.MUSHROOM_FIELDS, Biome.SNOWY_BEACH,
            Biome.SNOWY_PLAINS, Biome.SNOWY_SLOPES, Biome.SNOWY_TAIGA, Biome.STONY_PEAKS, Biome.STONY_SHORE,
            Biome.WINDSWEPT_HILLS, Biome.WOODED_BADLANDS};

    Biome[] belowTheAverage = {Biome.LUSH_CAVES, Biome.MANGROVE_SWAMP, Biome.OLD_GROWTH_SPRUCE_TAIGA, Biome.SWAMP,
            Biome.TAIGA, Biome.WINDSWEPT_SAVANNA, Biome.WINDSWEPT_GRAVELLY_HILLS};
    Biome[] aboveAverage = {Biome.DEEP_LUKEWARM_OCEAN, Biome.LUKEWARM_OCEAN, Biome.OCEAN};
    Biome[] coolBiomes = {Biome.BAMBOO_JUNGLE, Biome.JUNGLE, Biome.FOREST, Biome.OLD_GROWTH_BIRCH_FOREST, Biome.SPARSE_JUNGLE,
            Biome.WARM_OCEAN};

    Biome[] veryCoolBiomes = {Biome.RIVER, Biome.BIRCH_FOREST, Biome.CHERRY_GROVE, Biome.FLOWER_FOREST, Biome.MEADOW,
            Biome.PLAINS, Biome.SUNFLOWER_PLAINS};

    private final Plugin mainPlugin;

    public Grow(Plugin mainPlugin) {
        this.mainPlugin = mainPlugin;
    }

    CropState previousState = null;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (Arrays.asList(aboveAverage).contains(block.getBiome())) {
            if ((block.getType() == Material.WHEAT) || (block.getType() == Material.CARROTS) || (block.getType() == Material.POTATOES)) {
                setCustomTickSpeed(block, 50, 1);
            }
        }
        else if (Arrays.asList(coolBiomes).contains(block.getBiome())) {
            if ((block.getType() == Material.WHEAT) || (block.getType() == Material.CARROTS) || (block.getType() == Material.POTATOES)) {
                setCustomTickSpeed(block, 75, 2);
            }
        }
        else if (Arrays.asList(veryCoolBiomes).contains(block.getBiome())) {
            if ((block.getType() == Material.WHEAT) || (block.getType() == Material.CARROTS) || (block.getType() == Material.POTATOES)) {
                setCustomTickSpeed(block, 100, 3);
            }
        }

        else if (Arrays.asList(belowTheAverage).contains(block.getBiome()))
        {
            if (block.getType() == Material.WHEAT)
            {
                BlockState state = block.getState();
                if (state.getData() instanceof Crops) {
                    Crops crops = (Crops) state.getData();
                    CropState currentState = crops.getState();
                    if (currentState != previousState) {
                        if (crops.getState() == CropState.VERY_SMALL) {
                            crops.setState(CropState.GERMINATED);
                        }
                        state.update();
                        previousState = currentState;
                    }
                }
            }
             else if ((block.getType() == Material.CARROTS) || (block.getType() == Material.POTATOES)
                || (block.getType() == Material.BEETROOTS) || (block.getType() == Material.PUMPKIN_SEEDS) || (block.getType() == Material.MELON_SEEDS)){
                BlockState state = block.getState();
                if (state.getData() instanceof Crops) {
                    Crops crops = (Crops) state.getData();
                    CropState currentState = crops.getState();
                    if (currentState != previousState) {
                        if (crops.getState() == CropState.SMALL) {
                            crops.setState(CropState.GERMINATED);
                        }
                        state.update();
                        previousState = currentState;
                    }
                }
            }
        }

        else if (Arrays.asList(badBiomes).contains(block.getBiome()))
        {
            if (block.getType() == Material.WHEAT)
            {
                BlockState state = block.getState();
                if (state.getData() instanceof Crops) {
                    Crops crops = (Crops) state.getData();
                    CropState currentState = crops.getState();
                    if (currentState != previousState) {
                        if (crops.getState() == CropState.VERY_SMALL) {
                            crops.setState(CropState.GERMINATED);
                        }
                        if (crops.getState() == CropState.MEDIUM) {
                            crops.setState(CropState.SMALL);
                        }
                        state.update();
                        previousState = currentState;
                    }
                }
            }
            else if ((block.getType() == Material.CARROTS) || (block.getType() == Material.POTATOES)
                    || (block.getType() == Material.BEETROOTS) || (block.getType() == Material.PUMPKIN_SEEDS) || (block.getType() == Material.MELON_SEEDS)) {
                BlockState state = block.getState();
                if (state.getData() instanceof Crops) {
                    Crops crops = (Crops) state.getData();
                    CropState currentState = crops.getState();
                    if (currentState != previousState) {
                        if (crops.getState() == CropState.SMALL) {
                            crops.setState(CropState.GERMINATED);
                        }
                        if (crops.getState() == CropState.TALL) {
                            crops.setState(CropState.MEDIUM);
                        }
                        state.update();
                        previousState = currentState;
                    }
                }
            }
        }

        else if (Arrays.asList(veryBadBiomes).contains(block.getBiome()))
        {
            if (block.getType() == Material.WHEAT)
            {
                BlockState state = block.getState();
                if (state.getData() instanceof Crops) {
                    Crops crops = (Crops) state.getData();
                    CropState currentState = crops.getState();
                    if (currentState != previousState) {
                        if (crops.getState() == CropState.VERY_SMALL) {
                            crops.setState(CropState.GERMINATED);
                        }
                        if (crops.getState() == CropState.MEDIUM) {
                            crops.setState(CropState.SMALL);
                        }
                        if (crops.getState() == CropState.VERY_TALL) {
                            crops.setState(CropState.TALL);
                        }
                        state.update();
                        previousState = currentState;
                    }
                }
            }
            else if ((block.getType() == Material.CARROTS) || (block.getType() == Material.POTATOES)
                    || (block.getType() == Material.BEETROOTS) || (block.getType() == Material.PUMPKIN_SEEDS) || (block.getType() == Material.MELON_SEEDS)) {
                BlockState state = block.getState();
                if (state.getData() instanceof Crops) {
                    Crops crops = (Crops) state.getData();
                    CropState currentState = crops.getState();
                    if (currentState != previousState) {
                        if (crops.getState() == CropState.GERMINATED) {
                            crops.setState(CropState.SEEDED);
                        }
                        if (crops.getState() == CropState.MEDIUM) {
                            crops.setState(CropState.SMALL);
                        }
                        if (crops.getState() == CropState.RIPE) {
                            crops.setState(CropState.TALL);
                        }
                        state.update();
                        previousState = currentState;
                    }
                }
            }
        }
    }

    private void setCustomTickSpeed(Block block, int tickSpeed, int age) {
        Ageable ageable = (Ageable) block.getBlockData();
        ageable.setAge(age);
        block.setBlockData(ageable);
        block.setMetadata("customTickSpeed", new FixedMetadataValue(mainPlugin, tickSpeed));
    }

}

