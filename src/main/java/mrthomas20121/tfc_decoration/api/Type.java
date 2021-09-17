package mrthomas20121.tfc_decoration.api;

public class Type {

    private final String name;

    public Type(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }
}
