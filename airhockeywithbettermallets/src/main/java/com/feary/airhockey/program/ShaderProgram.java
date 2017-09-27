package com.feary.airhockey.program;

import android.content.Context;

import com.feary.util.ShaderHelper;
import com.feary.util.TextResourceReader;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by feary on 2017/9/25.
 */

public class ShaderProgram {
    //Uniform constans
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

    //Attribute constans
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    //shader program
    protected final int program;

    protected ShaderProgram(Context context, int vertexShaderResourceId, int fragmentShaderResourceId) {
        //拿到着色器GLSL文件，用buildProgram构建Program
        program = ShaderHelper.buildProgram(
                TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
                TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId));
    }

    public void useProgram() {
        glUseProgram(program);
    }

}
