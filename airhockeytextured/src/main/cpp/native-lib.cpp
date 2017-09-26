#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_feary_airhockey_AirHockeyActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from Java_com_feary_airhockey_AirHockeyActivity_stringFromJNI++";
    return env->NewStringUTF(hello.c_str());
}
