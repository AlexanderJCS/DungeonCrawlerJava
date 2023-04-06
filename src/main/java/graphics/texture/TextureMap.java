package graphics.texture;

import java.util.HashMap;

public class TextureMap {
    private static final String texturePath = "src/main/resources/";

    private static final HashMap<String, Texture> textureMap = new HashMap<>() {{
        put("player", new Texture(texturePath + "test.png"));
        put("white", new Texture(texturePath + "white.png"));
    }};

    public static Texture get(String key) throws IllegalArgumentException {
        Texture returnTexture = textureMap.get(key);

        if (returnTexture == null) {
            throw new IllegalArgumentException(key + " not found in textureMap");
        }

        return returnTexture;
    }
}
