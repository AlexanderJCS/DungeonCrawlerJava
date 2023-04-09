package graphics.texture;

import java.util.HashMap;


/**
 * This class is important because:
 * - It allows GameObjects to be serialized without serializing images with it
 * - Reduces memory when the program is running, since all images only need to be stored in memory once.
 */
public class TextureMap {
    private static final String texturePath = "src/main/resources/textures/";

    private static final HashMap<String, Texture> textureMap = new HashMap<>() {{
        put("playerNortheast", new Texture(texturePath + "player/northeast.png"));
        put("playerNorthwest", new Texture(texturePath + "player/northwest.png"));
        put("playerSoutheast", new Texture(texturePath + "player/southeast.png"));
        put("playerSouthwest", new Texture(texturePath + "player/southwest.png"));

        put("brick", new Texture(texturePath + "brick.png"));
        put("white", new Texture(texturePath + "white.png"));

        put("transparent", new Texture(texturePath + "transparent.png"));
    }};

    public static Texture get(String key) throws IllegalArgumentException {
        Texture returnTexture = textureMap.get(key);

        if (returnTexture == null) {
            throw new IllegalArgumentException(key + " not found in textureMap");
        }

        return returnTexture;
    }
}
