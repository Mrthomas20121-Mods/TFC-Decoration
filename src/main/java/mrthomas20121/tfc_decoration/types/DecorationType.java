package mrthomas20121.tfc_decoration.types;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum DecorationType {
    PILLAR(Material.ROCK, SoundType.STONE, 5, false),
    MOSSY_COBBLE(Material.ROCK, SoundType.STONE, 5, true),
    MOSSY_BRICKS(Material.ROCK, SoundType.STONE, 5, false),
    CRACKED_BRICKS(Material.ROCK, SoundType.STONE, 5, false),
    SANDSTONE(Material.ROCK, SoundType.STONE, 1, false),
    RAW_MUD(Material.ROCK, SoundType.STONE, 4, false),
    MUD_BRICKS(Material.ROCK, SoundType.STONE, 4, false),
    MUD_PILLAR(Material.ROCK, SoundType.STONE, 5, false),
	SANDSTONE_PILLAR(Material.ROCK, SoundType.STONE, 5, false),
    ROCKWOOL(Material.ROCK, SoundType.CLOTH, 5, false);

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
