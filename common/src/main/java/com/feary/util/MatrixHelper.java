package com.feary.util;

/**
 * Created by feary on 2017/9/19.
 */

public class MatrixHelper {

    /**
     * {@link android.opengl.Matrix#perspectiveM}
     * @param m 矩阵
     * @param yFovInDegrees 视野
     * @param aspect 屏幕的宽高比
     * @param f 到远处平面的距离
     * @param n 到近处平面的距离 if(n==1) -> z=-1
     */
    public static void perspectiveM(float[] m, float yFovInDegrees, float aspect, float n, float f) {
        final float angleInRadians = (float) (yFovInDegrees * Math.PI / 180.0);//焦距
        final float a = (float) (1.0 / Math.tan(angleInRadians / 2.0));
        m[0] = a / aspect;
        m[1] = 0f;
        m[2] = 0f;
        m[3] = 0f;

        m[4] = 0f;
        m[5] = a;
        m[6] = 0f;
        m[7] = 0f;

        m[8] = 0f;
        m[9] = 0f;
        m[10] = -((f + n) / (f - n));
        m[11] = -1f;

        m[12] = 0f;
        m[13] = 0f;
        m[14] = -((2f * f * n) / (f - n));
        m[15] = 0f;
    }
}
