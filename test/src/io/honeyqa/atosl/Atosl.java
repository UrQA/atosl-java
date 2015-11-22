package io.honeyqa.atosl;

import java.util.ArrayList;

public class Atosl {

native String[] findArch(String dSYM);
native String[] checkUUID(String arch, String dSYM);
native String[] symbolicate(String arch, String dSYM, String[] addresses, int addressLength);

static {
        System.loadLibrary("atosl");
}

public ArrayList<String> getArch(String dSYM){
        Atosl atosl = new Atosl();
        String[] arch = atosl.findArch(dSYM);
        if(Integer.parseInt(arch[0]) == 0) {
                ArrayList<String> result = new ArrayList<String>();
                for(int i = 0; i < Integer.parseInt(arch[1]); i++) {
                        result.add(arch[i+2]);
                }
                return result;
        }else{
                return null;
        }
}

public String getUUID(String arch, String dSYM){
        Atosl atosl = new Atosl();
        String[] found = atosl.checkUUID(arch, dSYM);
        if(Integer.parseInt(found[0]) == 0) {
                return found[1].toUpperCase()
                       .replaceFirst("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)",
                                     "$1-$2-$3-$4-$5");
        }else{
                return null;
        }
}

public ArrayList<String> doSymbolicate(String arch, String dSYM, String[] addresses){
        Atosl atosl = new Atosl();
        String[] result = atosl.symbolicate(arch, dSYM, addresses, addresses.length);
        if(Integer.parseInt(result[0]) == 0) {
                ArrayList<String> symbolicated = new ArrayList<String>();
                for(int i = 1; i < addresses.length; i++) {
                        if(result[i].equals(addresses[i])) {
                                // Not symbolicated
                                symbolicated.add("");
                        }else{
                                // symbolicated
                                symbolicated.add(result[i]);
                        }
                }
                return symbolicated;
        }else{
                return null;
        }
}

}
