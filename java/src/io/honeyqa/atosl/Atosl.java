package io.honeyqa.atosl;

public class Atosl{
    native String[] findArch(String dSYM);
    native String[] symbolicate(String arch, String dSYM, String[] addresses, int addressLength);
    static {
        System.loadLibrary("atosl");
    }

    public static void main(String[] args){
        System.out.println("[!] honeyqa atosl");
    }
}