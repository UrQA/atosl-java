#include "jni.h"
#include <stdio.h>
#include "hqatosl.h"

extern char *project_name;

static int lookup_by_address(struct thin_macho *thin_macho, CORE_ADDR integer_address){
        int result = -1;
        if(thin_macho->dwarf2_per_objfile != NULL) {
                result = lookup_by_address_in_dwarf(thin_macho, integer_address);
        }
        if(result == -1) {
                result = lookup_by_address_in_symtable(thin_macho, integer_address);
        }
        return result;
}

static void set_project_name(const char* full_filename){
        char *filename = strrchr(full_filename, '/');
        if(filename == NULL) {
                filename = (char *)full_filename;
        }else{
                filename = filename + 1;
        }
        project_name = filename;
}

JNIEXPORT jint JNICALL Java_Atosl_symbolicate
        (JNIEnv * env, jobject jobj, jstring a, jstring e, jobjectArray adr, jint adrl){
        const char *arch = (*env)->GetStringUTFChars(env, a, NULL);
        const char *executable = (*env)->GetStringUTFChars(env, e, NULL);
        // symbolicate
        set_project_name(executable);
        struct target_file *tf = parse_file(executable);
        if (tf == NULL) {
                return -1;
        }
        struct thin_macho *thin_macho = NULL;
        // TODO : performance
        int i = select_thin_macho_by_arch(tf, arch);
        if(i == -1) {
                printf("atosl: Can not find macho for architecture: %s.\n", arch);
                return -1;
        }
        thin_macho = tf->thin_machos[i];
        if(thin_macho->dwarf2_per_objfile != NULL) {
                parse_dwarf2_per_objfile(thin_macho->dwarf2_per_objfile);
        }
        // numeric_to_symbols
        int j = 0;
        CORE_ADDR integer_address = 0;
        for (j = 0; j < adrl; j++) {
                jstring element = (jstring) (*env)->GetObjectArrayElement(env, adr, j);
                const char *address = (*env)->GetStringUTFChars(env, element, 0);
                if(address[0] == '0' && (address[1] == 'x' || address[1] == 'X')) {
                        //address start with 0x
                        integer_address = strtoll(address, NULL, 0);
                }else{
                        integer_address= strtoll(address, NULL, 16);
                }
                if (lookup_by_address(thin_macho, integer_address) != 0) {
                        printf("%s\n", address);
                }
                // Release
                (*env)->ReleaseStringUTFChars(env, element, address);
        }
        // end numeric_to_symbols
        free_target_file(tf);
        // end symbolicate
        // Release
        (*env)->ReleaseStringUTFChars(env, a, arch);
        (*env)->ReleaseStringUTFChars(env, e, executable);
        return 0;
}
