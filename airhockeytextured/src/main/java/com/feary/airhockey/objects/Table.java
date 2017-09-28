package com.feary.airhockey.objects;

import com.feary.airhockey.program.TextureShaderProgragm;
import com.feary.util.Constants;
import com.feary.util.VertexArray;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by feary on 2017/9/21.
 */

public class Table {
    private static final int POSITION_COMPONENT_COUNT = 2; //距离数组长度,x,y
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2; //纹理坐标数组长度,S,T
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;
   //循环的步长
    private static final float[] VERTEX_DATA = {
           //Order of coordinates: X, Y, S, T
           // 桌子的的 x y 位置，以及 S T 纹理坐标
           // Triangle Fan
            0f, 0f, 0.5f, 0.5f,
            -0.5f, -0.8f, 0f, 0.9f,
            0.5f, -0.8f, 1f, 0.9f,
            0.5f, 0.8f, 1f, 0.1f,
            -0.5f, 0.8f, 0f, 0.1f,
            -0.5f, -0.8f, 0f, 0.9f
           //这里坐标真心傻逼，为什么不用一套？？？？
    };

    private final VertexArray vertexArray;

    public Table() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgragm textureProgram){

        //从着色器构建的程序中获取每一个属性的位置，
        //通过getPositionAttributeLocation把程序位置属性绑定到着色器位置属性上
        //通过getTextureCoordinatesAttributeLocation把程序纹理位置属性绑定到着色器属纹理置属性上
        vertexArray.setVertexAttribPointer(
                0,
                textureProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);

        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordnatesLocation(),
                TEXTURE_COORDINATES_COMPONENT_COUNT,
                STRIDE);
    }

    public void draw(){
        glDrawArrays(GL_TRIANGLE_FAN,0,6);
    }




}
