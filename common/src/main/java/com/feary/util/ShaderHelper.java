package com.feary.util;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;

/**
 * Created by feary on 2017/9/16.
 */

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";
    private static final int ERROR_CODE = 0;

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    public static int compileShader(int type, String shaderCode) {
        final int shaderObjectId = glCreateShader(type);
        if (shaderObjectId == ERROR_CODE) {
            Logger.w(TAG, "Couldn't create new shader.");
            return ERROR_CODE;
        }
        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);
        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        Logger.v(TAG, "Results of compiling source:" + "\n" + shaderCode + "\n" + glGetShaderInfoLog(shaderObjectId));
        if (compileStatus[0] == ERROR_CODE) {
            glDeleteShader(shaderObjectId);
            Logger.w(TAG, "ComPilation of shader failed");
            return ERROR_CODE;
        }
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        final int programObjectId = glCreateProgram();
        if (programObjectId == ERROR_CODE) {
            Logger.w(TAG, "Could not create new program");
            return ERROR_CODE;
        }
        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId);
        glLinkProgram(programObjectId);
        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);
        Logger.v(TAG, "Results of linking program:\n" + glGetShaderInfoLog(programObjectId));
        if (linkStatus[0] == ERROR_CODE) {
            glDeleteProgram(programObjectId);
            Logger.w(TAG, "Linking of program failed.");
            return ERROR_CODE;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId) {
        glValidateProgram(programObjectId);

        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        Logger.v(TAG, "REsults of validating program: " + validateStatus[0] + "\nLog: " + glGetProgramInfoLog(programObjectId));
        return validateStatus[0] != ERROR_CODE;
    }

    //程序构建器
    public static int buildProgram(String vertexShaderSource, String fragmentShaderSource) {
        int program;

        //编译着色器
        int vertexShader = compileVertexShader(vertexShaderSource);
        int fragmentShader = compileFragmentShader(fragmentShaderSource);

        //将着色器连接到程序上
        program = linkProgram(vertexShader, fragmentShader);
        validateProgram(program);
        return program;

    }

    ;

}
