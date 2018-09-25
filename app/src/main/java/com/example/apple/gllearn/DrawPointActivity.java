package com.example.apple.gllearn;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class DrawPointActivity extends MainActivity {

    private float[] points = {
        0f, 0.4f, 0f,
        -0.4f, -0.2f, 0f,
        0.4f, -0.2f, 0f,
    };
    @Override
    public void onDrawFrame(GL10 gl) {
        long timeMillis = System.currentTimeMillis();
        Log.i("lijing", "time == "+ (timeMillis - time));
        time = timeMillis;

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(points.length *4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(points);
        floatBuffer.position(0);
        gl.glColor4f(1f, 0f, 0f, 1f);
        gl.glPointSize(18f);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);
        gl.glDrawArrays(GL10.GL_POINTS, 0, 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
