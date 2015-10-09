// JNI
#include "jni.h"

#include <stdio.h>

// Facebook atosl header file (including Architecture information, +)
#include "fb_atosl.h"

JNIEXPORT jstring JNICALL Java_Atosl_getArchitecture
        (JNIEnv *env, jobject jobj, jstring dSYM){
}

JNIEXPORT jint JNICALL Java_Atosl_symbolicate
        (JNIEnv * env, jobject jobj, jstring arch, jstring dSYM, jobjectArray adrarr, jint adrlen){
}
