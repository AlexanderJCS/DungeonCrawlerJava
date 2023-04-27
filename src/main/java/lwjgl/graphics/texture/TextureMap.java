package lwjgl.graphics.texture;

import java.util.HashMap;

import static lwjgl.graphics.texture.TextureType.*;

/**
 * This class is important because:
 * - It allows Images to be serialized without serializing the texture with it
 * - Reduces memory when the program is running, since all images only need to be stored in memory once.
 */
public class TextureMap {
    private static final String texturePath = "src/main/resources/textures/";

    private static final HashMap<TextureType, Texture> textureMap = new HashMap<>() {{
        // -- GAME OBJECTS --
        put(PLAYER_NORTHEAST, new Texture(texturePath + "gameObjects/player/northeast.png"));
        put(PLAYER_NORTHWEST, new Texture(texturePath + "gameObjects/player/northwest.png"));
        put(PLAYER_SOUTHEAST, new Texture(texturePath + "gameObjects/player/southeast.png"));
        put(PLAYER_SOUTHWEST, new Texture(texturePath + "gameObjects/player/southwest.png"));

        put(BRICK, new Texture(texturePath + "gameObjects/BRICK.png"));

        put(CHEST_CLOSED, new Texture(texturePath + "gameObjects/chestClosed.png"));
        put(CHEST_OPEN, new Texture(texturePath + "gameObjects/chestOpen.png"));

        put(ENEMY, new Texture(texturePath + "gameObjects/enemy.png"));

        // -- ITEMS --
        put(HEART, new Texture(texturePath + "items/heart.png"));
        put(HALF_HEART, new Texture(texturePath + "items/halfHeart.png"));
        put(EMPTY_HEART, new Texture(texturePath + "items/emptyHeart.png"));

        put(SWORD, new Texture(texturePath + "items/sword.png"));

        // -- COLORS --
        put(WHITE, new Texture(texturePath + "white.png"));
        put(RED, new Texture(texturePath + "red.png"));
        put(YELLOW, new Texture(texturePath + "yellow.png"));
        put(GREEN, new Texture(texturePath + "green.png"));

        put(TRANSPARENT, new Texture(texturePath + "transparent.png"));
    }};

    public static Texture get(TextureType key) throws IllegalArgumentException {
        Texture returnTexture = textureMap.get(key);

        if (returnTexture == null) {
            throw new IllegalArgumentException(key + " not found in textureMap");
        }

        return returnTexture;
    }
}
