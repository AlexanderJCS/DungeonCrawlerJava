package graphics;

import static org.lwjgl.opengl.GL21.*;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;


/**
 * Taken from:
 * <a href="https://www.youtube.com/watch?v=-6P_CkT-FlQ&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u&index=5&ab_channel=WarmfulDevelopment">...</a>
 * Model class, which allows a shape to be drawn to the screen using VBOs.
 */
public class Model {
    protected final int drawCount;
    protected final int vId;
    protected final static int DIMENSIONS = 2;
    protected FloatBuffer vboBuffer;


    public Model(float[] vertices) {
        drawCount = vertices.length / DIMENSIONS;

        vboBuffer = createBuffer(vertices);

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, vboBuffer, GL_DYNAMIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void render() {
        glEnableClientState(GL_VERTEX_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glVertexPointer(DIMENSIONS, GL_FLOAT, 0, 0);

        glDrawArrays(GL_TRIANGLES, 0, drawCount);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDisableClientState(GL_VERTEX_ARRAY);
    }

    /**
     * <a href="http://forum.lwjgl.org/index.php?topic=5334.0">...</a>
     */
    public void changeVertices(float[] vertices) {
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferSubData(GL_ARRAY_BUFFER, 0, updateBuffer(vboBuffer, vertices));
        glBindBuffer(GL_ARRAY_BUFFER, vId);
    }

    /**
     * <a href="http://forum.lwjgl.org/index.php?topic=5334.0">...</a>
     */
    private static FloatBuffer updateBuffer(FloatBuffer buffer, float[] vertices) {
        buffer.clear();  // clear buffer (it says this doesn't delete the data in the javadoc is this still going to remove the old data?)
        buffer.put(vertices);
        buffer.flip();  // flip the buffer (does buffer.clear() un-flip the buffer?, does this need to be called here still?)

        return buffer;
    }

    protected FloatBuffer createBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }
}
