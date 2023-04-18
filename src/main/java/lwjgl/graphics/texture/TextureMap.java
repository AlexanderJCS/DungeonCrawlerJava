package lwjgl.graphics.texture;

import java.util.HashMap;


/**
 * This class is important because:
 * - It allows Images to be serialized without serializing the texture with it
 * - Reduces memory when the program is running, since all images only need to be stored in memory once.
 */
public class TextureMap {
    private static final String texturePath = "src/main/resources/textures/";

    private static final HashMap<String, Texture> textureMap = new HashMap<>() {{
        // -- GAME OBJECTS --
        put("playerNortheast", new Texture(texturePath + "gameObjects/player/northeast.png"));
        put("playerNorthwest", new Texture(texturePath + "gameObjects/player/northwest.png"));
        put("playerSoutheast", new Texture(texturePath + "gameObjects/player/southeast.png"));
        put("playerSouthwest", new Texture(texturePath + "gameObjects/player/southwest.png"));

        put("brick", new Texture(texturePath + "gameObjects/brick.png"));

        put("chestClosed", new Texture(texturePath + "gameObjects/chestClosed.png"));
        put("chestOpen", new Texture(texturePath + "gameObjects/chestOpen.png"));

        put("enemy", new Texture(texturePath + "gameObjects/enemy.png"));

        // -- ITEMS --
        put("heart", new Texture(texturePath + "items/heart.png"));
        put("halfHeart", new Texture(texturePath + "items/halfHeart.png"));
        put("emptyHeart", new Texture(texturePath + "items/emptyHeart.png"));

        put("sword", new Texture(texturePath + "items/sword.png"));

        // -- COLORS --
        put("white", new Texture(texturePath + "white.png"));
        put("red", new Texture(texturePath + "red.png"));
        put("yellow", new Texture(texturePath + "yellow.png"));
        put("green", new Texture(texturePath + "green.png"));

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
