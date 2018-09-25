package com.example.apple.gllearn;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class DrawLineActivity extends MainActivity {
    private float[] points = {
            -0.25f, 0.25f, 0f,
            -0.5f, -0.25f, 0f,
            0.5f, 0.25f, 0f,
            0.25f, -0.25f, 0f,
    };

    protected int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        new Thread() {
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(1000);
                    index++;

                    glSurfaceView.requestRender();
                }

            }
        }.start();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(points.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(points);
        floatBuffer.position(0);


        gl.glLineWidth(5f);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);

        int i = index % 3;
        switch (i) {
            case 0:
                gl.glColor4f(1f, 1f, 0f, 1f);
                gl.glDrawArrays(GL10.GL_LINES, 0, 4);
                break;
            case 1:
                gl.glColor4f(0f, 1f, 1f, 1f);
                gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
                break;
            case 2:
                gl.glColor4f(1f, 0f, 1f, 1f);
                gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
                break;
            default:
                break;
        }


        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
