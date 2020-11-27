package mrthomas20121.tfc_decoration.types;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum DecorationType {
    MOSSY_COBBLE(Material.ROCK, SoundType.STONE, 5, true),
    MOSSY_BRICK(Material.ROCK, SoundType.STONE, 5, false),
    CRACKED_BRICK(Material.ROCK, SoundType.STONE, 5, false),
    WET_MUD(Material.CLAY, SoundType.SAND, 3, false),
    MUD(Material.CLAY, SoundType.SAND, 2, true);

    private Material material;
    private SoundType soundType;
    private int hardness;
    private boolean fallable;

    DecorationType(Material material, SoundType soundType, int hardness, boolean isFallable)
    {
        this.soundType = soundType;
        this.material = material;
        this.hardness = hardness;
        this.fallable = isFallable;
    }

    public Material getMaterial() {
        return material;
    }

    public SoundType getSoundType() {
        return soundType;
    }

    public boolean isFallable() {
        return fallable;
    }

    public int getHardness() {
        return hardness;
    }
}
