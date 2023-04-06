package graphics.texture;

import static org.lwjgl.opengl.GL21.*;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Following a tutorial:
 * <a href="https://www.youtube.com/watch?v=crOzRjzqI-o&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u&index=4&ab_channel=WarmfulDevelopment">...</a>
 * Allows images to be drawn to the screen. To use it, run Texture.bind() then draw the shape you want to map it to.
 */
public class Texture {
    private int id;

    public Texture(String filename) {
        BufferedImage bufferedImage;

        try {
            bufferedImage = ImageIO.read(new File(filename));
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            int[] pixels_raw = new int[width * height * 4];
            pixels_raw = bufferedImage.getRGB(0, 0, width, height, null, 0, width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int pixel = pixels_raw[i * width + j];
                    pixels.put((byte)((pixel >> 16) & 0xFF));  // Red
                    pixels.put((byte)((pixel >> 8) & 0xFF));   // Green
                    pixels.put((byte)(pixel & 0xFF));          // Blue
                    pixels.put((byte)((pixel >> 24) & 0xFF));  // Alpha
                }
            }

            pixels.flip();
            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
}
