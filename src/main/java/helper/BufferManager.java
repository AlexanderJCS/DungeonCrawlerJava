package helper;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class BufferManager {
    public static FloatBuffer vboBuffer = BufferUtils.createFloatBuffer(1024);
    public static FloatBuffer texCoordsBuffer = BufferUtils.createFloatBuffer(1024);

    public static void setBuffer(FloatBuffer fb, float[] newData) throws IllegalArgumentException {
        if (newData.length > 1024) {
            throw new IllegalArgumentException(
                    "newData is greater than length 1024, which will cause a buffer overflow exception");
        }

        fb.clear();
        fb.limit(newData.length);
        fb.put(newData);
        fb.clear();
    }
}
