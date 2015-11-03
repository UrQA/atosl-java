import java.util.ArrayList;
import java.util.UUID;

public class Atosl {
native String[] findArch(String dSYM);
native String[] checkUUID(String arch, String dSYM);
native String[] symbolicate(String arch, String dSYM, String[] addresses, int addressLength);

static {
        System.load("atosl_library_path");
}

public ArrayList<String> getArch(String dSYM){
        Atosl atosl = new Atosl();
        String[] arch = atosl.findArch(dSYM);
        if(Integer.parseInt(arch[0]) == 0) {
                ArrayList<String> result = new ArrayList<String>();
                for(int i = 0; i < Integer.parseInt(arch[1]); i++) {
                        System.out.println(arch[i+2]);
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
                        System.out.println(symbolicated.get(i-1));
                }
                return symbolicated;
        }else{
                return null;
        }
}

static public void main(String argv[]) {
        String test_arch = "arm64";
        String test_dSYM = "./res/m2048.app.dSYM/Contents/Resources/DWARF/m2048";
        String test1_Address[] = {"0x1000dc000","0x00000001000e43a4","0x0000000d1000f43a4"};
        // findArch & getUUID
        Atosl atosl = new Atosl();
        ArrayList<String> arch = atosl.getArch(test_dSYM);
        if(arch!=null) {
                for(int i=0; i<arch.size(); i++) {
                        System.out.println(arch.get(i) + "UUID");
                        String uuid = atosl.getUUID(arch.get(i), test_dSYM);
                        System.out.println(uuid);
                }
        }
        // Symbolication
        ArrayList<String> data = atosl.doSymbolicate(test_arch,
                              test_dSYM,
                              test1_Address);
}
}
