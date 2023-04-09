package graphics.texture;

import graphics.Model;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL21.*;


/**
 * Taken from this link:
 * <a href="https://www.youtube.com/watch?v=-6P_CkT-FlQ&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u&index=5&ab_channel=WarmfulDevelopment">...</a>
 * A Model class that allows textures to be drawn to it.
 */
public class TexturedModel extends Model {
    private final int tId;

    public TexturedModel(float[] vertices, float[] texCoords) {
        super(vertices);

        tId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(texCoords), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void render() {
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glVertexPointer(DIMENSIONS, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, tId);
        glTexCoordPointer(2, GL_FLOAT, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, drawCount);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
    }
}
