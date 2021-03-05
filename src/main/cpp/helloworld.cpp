#include <jni.h>
#include <string>
  
#include<iostream>

extern "C" JNIEXPORT jstring JNICALL
Java_game_div_cookiecounter_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

int main(int argc, char *argv[]){
   std::cout << "Hello World!" << std::endl;
   return 0;
}

