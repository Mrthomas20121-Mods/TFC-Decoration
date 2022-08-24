package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class DecLanguageProvider extends LanguageProvider {

    public DecLanguageProvider(DataGenerator gen) {
        super(gen, TFCDecoration.mod_id, "en_us");
    }

    @Override
    protected void addTranslations() {
        Arrays.stream(Rock.values()).forEach(rock -> {
            add(TFCDecBlocks.PILLARS.get(rock).get(), capitalize(rock.getSerializedName())+" Pillar");
            add(TFCDecBlocks.ROCKWOOL.get(rock).get(), capitalize(rock.getSerializedName())+" Rockwool");
        });

        add(TFCDecBlocks.POLISHED_FIRE_CLAY.get(), "Polished Fire Clay");
    }

    public String capitalize(String input) {
        if(input.contains("_")) {
            String[] split = input.split("_");
            StringBuilder output = new StringBuilder();
            for(String s : split) {
                output.append(StringUtils.capitalize(s));
                output.append(" ");
            }
            return output.toString().trim();
        }
        return StringUtils.capitalize(input);
    }
}
