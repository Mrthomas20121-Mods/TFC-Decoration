package mrthomas20121.tfc_decoration.datagen.tfc;

import com.google.gson.JsonObject;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface TFCData {

    JsonObject toJson();

    ID getID();

    enum ID implements StringRepresentable {
        ITEM_HEAT,
        ITEM_SIZE;

        @Override
        public @NotNull String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
