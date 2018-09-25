package com.example.apple.gllearn;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class DrawTriangleActivity extends MainActivity {
    private float points[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,
            0.0f, -0.0f * 1.732f, 0.0f,
            0.8f, -0.0f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        new Thread() {
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(1000);
                    glSurfaceView.requestRender();
                    index++;
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

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, floatBuffer);

        int i = index % 3;
        switch (i) {
            case 0:
                gl.glColor4f(0.5f, 1f, 1f, 1f);
                gl.glDrawArrays(GL10.GL_TRIANGLES, 0, points.length);
                break;
            case 1:
                gl.glColor4f(1f, 1f, 0.5f, 1f);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, points.length);
                break;
            case 2:
                gl.glColor4f(1f, 0.5f, 1f, 1f);
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, points.length);
                break;
            default:
                break;
        }

        gl.glPointSize(20f);
        gl.glColor4f(1f,1f, 1f, 1f);
        gl.glDrawArrays(GL10.GL_POINTS, 0, points.length);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
