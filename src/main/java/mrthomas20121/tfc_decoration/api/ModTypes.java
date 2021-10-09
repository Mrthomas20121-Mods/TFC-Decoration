package mrthomas20121.tfc_decoration.api;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to take care of the wood/rock types
 */
public class ModTypes {

    private static final HashMap<Tree, HashMap<WoodType, Block>> woodTable = new HashMap<>();
    private static final HashMap<Rock, HashMap<RockType, Block>> rockTable = new HashMap<>();
    private static final HashMap<Rock, HashMap<ItemRockType, Item>> rockItemTable = new HashMap<>();

    private static final ArrayList<WoodType> woodTypes = new ArrayList<>();
    private static final ArrayList<RockType> rockTypes = new ArrayList<>();
    private static final ArrayList<ItemRockType> itemRockTypes = new ArrayList<>();

    public static ArrayList<RockType> getRockTypes() {
        return rockTypes;
    }

    public static ArrayList<WoodType> getWoodTypes() {
        return woodTypes;
    }

    public static ArrayList<ItemRockType> getItemRockTypes() {
        return itemRockTypes;
    }

    public static WoodType FENCE_LOG;
    public static WoodType LADDER;
    public static WoodType WOODEN_BRICKS;

    public static RockType PILLAR;
    public static RockType MOSSY_COBBLE;
    public static RockType CRACKED_BRICKS;
    public static RockType MOSSY_BRICKS;
    public static RockType SANDSTONE;
    public static RockType SANDSTONE_PILLAR;
    public static RockType RAW_MUD;
    public static RockType MUD_BRICKS;
    public static RockType MUD_PILLAR;
    public static RockType ROCKWOOL;

    public static ItemRockType MUD_BALL;
    public static ItemRockType MUD_BRICK;

    public static void init() {
        // wood types
        FENCE_LOG = addWoodType("fence_log");
        LADDER = addWoodType("ladder");
        // WOODEN_BRICKS = addWoodType("bricks");

        // rock types
        PILLAR = addRockType("pillar", SoundType.STONE, 5, false);
        MOSSY_COBBLE = addRockType("mossy_cobble", SoundType.STONE, 5, true);
        MOSSY_BRICKS = addRockType("mossy_bricks", SoundType.STONE, 5, false);
        CRACKED_BRICKS = addRockType("cracked_bricks", SoundType.STONE, 5, false);
        SANDSTONE = addRockType("sandstone", SoundType.STONE, 1, false);
        SANDSTONE_PILLAR = addRockType("sandstone_pillar", SoundType.STONE, 5, false);
        RAW_MUD = addRockType("raw_mud", SoundType.STONE, 4, false);
        MUD_BRICKS = addRockType("mud_bricks", SoundType.STONE, 4, false);
        MUD_PILLAR = addRockType("mud_pillar", SoundType.STONE, 5, false);
        ROCKWOOL = addRockType("rockwool", SoundType.CLOTH, 5, false);

        MUD_BALL = addItemRockType("mud_ball");
        MUD_BRICK = addItemRockType("mud_brick");
    }

    public static Block getWood(Tree tree, String wood_name) {
        WoodType woodType = woodTypes.stream().filter(type->type.getName().equals(wood_name)).findFirst().orElse(null);
        return getWood(tree, woodType);
    }

    public static Block getWood(Tree tree, WoodType type) {
        return woodTable.get(tree).get(type);
    }

    public static Block getRock(Rock rock, String rock_name) {
        RockType rockType = rockTypes.stream().filter(type->type.getName().equals(rock_name)).findFirst().orElse(null);
        return getRock(rock, rockType);
    }

    public static Block getRock(Rock rock, RockType type) {
        return rockTable.get(rock).get(type);
    }

    public static WoodType addWoodType(String name) {
        WoodType type = new WoodType(name);
        woodTypes.add(type);
        return type;
    }

    public static ItemRockType addItemRockType(String name) {
        ItemRockType type = new ItemRockType(name);
        itemRockTypes.add(type);
        return type;
    }

    public static RockType addRockType(String name, SoundType soundType, int hardness, boolean isFallable) {
        RockType type = new RockType(name, soundType, hardness, isFallable);
        rockTypes.add(type);
        return type;
    }

    public static void addWood(Tree tree, WoodType type, Block block) {
        if(!woodTable.containsKey(tree))
        {
            woodTable.put(tree, new HashMap<>());
        }
        woodTable.get(tree).put(type, block);
    }

    public static void addItemRock(Rock rock, ItemRockType type, Item item) {
        if(!rockItemTable.containsKey(rock))
        {
            rockItemTable.put(rock, new HashMap<>());
        }
        rockItemTable.get(rock).put(type, item);
    }

    public static void addRock(Rock rock, RockType type, Block block) {
        if(!rockTable.containsKey(rock))
        {
            rockTable.put(rock, new HashMap<>());
        }
        rockTable.get(rock).put(type, block);
    }

    public static class WoodType extends Type {

        public WoodType(String name) {
            super(name);
        }
    }

    public static class ItemRockType extends Type {

        public ItemRockType(String name) {
            super(name);
        }
    }

    public static class RockType extends Type {

        private final SoundType soundType;
        private final int hardness;
        private final boolean isFallable;

        public RockType(String name, SoundType soundType, int hardness, boolean isFallable) {
            super(name);
            this.soundType = soundType;
            this.hardness = hardness;
            this.isFallable = isFallable;
        }

        public SoundType getSoundType() {
            return soundType;
        }

        public int getHardness() {
            return hardness;
        }

        public boolean canFall() {
            return isFallable;
        }
    }
}
