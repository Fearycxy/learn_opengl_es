package com.feary.airhockey.program;

import android.content.Context;

import com.feary.airhockey.R;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by feary on 2017/9/25.
 */

public class TextureShaderProgragm extends ShaderProgram {
    //Uniform locations
    private final int uMatrixLocation;
    private final int uTextureUnitLocation;

    //Attribute locations
    private final int aPositionLocation;
    private final int aTextureCoordinatesLocation;


    public TextureShaderProgragm(Context context) {
        super(context, R.raw.texture_vertex_shader, R.raw.texture_fragment_shader);

        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);

        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aTextureCoordinatesLocation = glGetAttribLocation(program, A_TEXTURE_COORDINATES);
    }

    public void setUniforms(float[] matrix, int textureId) {
        //传递正交投影矩阵
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        //纹理单元保存纹理，GPU只能绘制有限的纹理，需要提高渲染速度的话，需要多个纹理单元保存纹理切换
        glActiveTexture(GL_TEXTURE0);
        //将纹理绑定到单元
        glBindTexture(GL_TEXTURE_2D, textureId);
        //将纹理单元传递给u_TextureUnit
        glUniform1i(uTextureUnitLocation, 0);//0，对应纹理第一层

    }

    public int getPositionAttributeLocation(){
        return aPositionLocation;
    }

    public int getTextureCoordnatesLocation(){
        return aTextureCoordinatesLocation;
    }
}
