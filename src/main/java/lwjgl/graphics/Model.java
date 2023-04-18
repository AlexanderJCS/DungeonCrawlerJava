package lwjgl.graphics;

import helper.BufferManager;

import static org.lwjgl.opengl.GL21.*;

/**
 * Modified from:
 * <a href="https://www.youtube.com/watch?v=-6P_CkT-FlQ&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u&index=5&ab_channel=WarmfulDevelopment">...</a>
 * Model class, which allows a shape to be drawn to the screen using VBOs.
 */
public class Model {
    protected final static int DIMENSIONS = 2;
    protected final int drawCount;
    protected final int vId;

    /**
     * Create a new model with the given vertices.
     *
     * @param vertices The triangle vertices.
     */
    public Model(float[] vertices) {
        drawCount = vertices.length / DIMENSIONS;

        BufferManager.setFloatBuffer(BufferManager.vboBuffer, vertices);

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, BufferManager.vboBuffer, GL_DYNAMIC_DRAW);
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
     * Clean up memory associated with glDeleteBuffers. This method is recommended to be called before the deletion
     * of the Model. This should be inside a destructor, but there aren't any destructors in Java so there's nothing
     * I can do
     */
    public void cleanup() {
        glDeleteBuffers(vId);
    }

    /**
     * Changes the vertices to the ones given. This is a memory-safe way to do so, since creating a new
     * Model will cause a memory leak.
     * <a href="http://forum.lwjgl.org/index.php?topic=5334.0">...</a>
     */
    public void changeVertices(float[] vertices) {
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        BufferManager.setFloatBuffer(BufferManager.vboBuffer, vertices);
        glBufferSubData(GL_ARRAY_BUFFER, 0, BufferManager.vboBuffer);
        glBindBuffer(GL_ARRAY_BUFFER, vId);
    }
}
