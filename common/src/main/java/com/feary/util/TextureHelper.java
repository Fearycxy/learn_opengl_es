package com.feary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_LINEAR_MIPMAP_LINEAR;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glTexParameteri;

/**
 * Created by feary on 2017/9/20.
 */

public class TextureHelper {
    private final static String TAG = "TextureHelper";

    public static int loadTexture(Context context, int resourceId) {
        //材质ID
        final int[] textureObjectIds = new int[1];
        //创建了材质纹理对象
        glGenTextures(1, textureObjectIds, 0);
        //检查调用是否成功
        if (textureObjectIds[0] == 0) {
            Logger.w(TAG, "Couldn't generate a new OpenGl texture object");
            return 0;
        }
        //OpenGL 不支持读取压缩图形格式如 JPEG PNG，所以要用Android内置解码器把图像压缩为OpenGL 所需格式
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        //图像解码
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        if (bitmap == null) {
            Logger.w(TAG, "Resource ID " + resourceId + " could not be decoded.");
            glDeleteTextures(1, textureObjectIds, 0);
            return 0;
        }
        //二维纹理，对象ID;
        // 历史遗留的垃圾api协议
        glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);

        //纹理扩大或缩小的时候，需要纹理过滤来告知，纹理元素挤压到更小的片段 - 缩小 ,纹理元素拓展到更大的 片段 - 放大
        //缩小 使用三线性过滤（更平滑）
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        //放大 双线性过滤（解决了最临界过滤放大的锯齿效果）
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        //告诉OpenGL读取Bitmap，并且绑定到纹理对象上
        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
        //生成MIP贴图
        glGenerateMipmap(GL_TEXTURE_2D);
        bitmap.recycle();
        //纹理加载成贴图后，解除纹理的绑定
        glBindTexture(GL_TEXTURE_2D, 0);
        return textureObjectIds[0];
    }
}
