package graphics;

import static org.lwjgl.opengl.GL21.*;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;


/**
 * Modified from:
 * <a href="https://www.youtube.com/watch?v=-6P_CkT-FlQ&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u&index=5&ab_channel=WarmfulDevelopment">...</a>
 * Model class, which allows a shape to be drawn to the screen using VBOs.
 */
public class Model {
    protected final int drawCount;
    protected final int vId;
    protected final static int DIMENSIONS = 2;
    protected FloatBuffer vboBuffer;

    /**
     * Create a new model with the given vertices.
     * @param vertices The triangle vertices.
     */
    public Model(float[] vertices) {
        drawCount = vertices.length / DIMENSIONS;

        vboBuffer = createBuffer(vertices);

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, vboBuffer, GL_DYNAMIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
     * Renders the model as a white box.
     */
    public void render() {
        glEnableClientState(GL_VERTEX_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glVertexPointer(DIMENSIONS, GL_FLOAT, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, drawCount);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDisableClientState(GL_VERTEX_ARRAY);
    }

    /**
     * Changes the vertices to the ones given. This is a memory-safe way to do so, since creating a new
     * Model will cause a memory leak.
     * <a href="http://forum.lwjgl.org/index.php?topic=5334.0">...</a>
     */
    public void changeVertices(float[] vertices) {
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferSubData(GL_ARRAY_BUFFER, 0, updateBuffer(vboBuffer, vertices));
        glBindBuffer(GL_ARRAY_BUFFER, vId);
    }

    /**
     * Adds the new vertices to the given buffer.
     * <a href="http://forum.lwjgl.org/index.php?topic=5334.0">...</a>
     *
     * @param buffer The buffer to add the vertices to.
     * @param vertices The vertices to add.
     * @return A reference to the new FloatBuffer.
     */
    private static FloatBuffer updateBuffer(FloatBuffer buffer, float[] vertices) {
        buffer.clear();
        buffer.put(vertices);
        buffer.flip();

        return buffer;
    }

    /**
     * Creates a new FloatBuffer.
     * @param data The vertices data.
     * @return The FloatBuffer containing the vertex data.
     */
    protected FloatBuffer createBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }
}
