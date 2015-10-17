public class Atosl {
native String[] findArch(String dSYM);
native String[] symbolicate(String arch, String dSYM, String[] addresses, int addressLength);
static {
        System.load("/Users/devholic/Desktop/git/honeyqa/atosl-java/test/libatosl.so");
}
static public void main(String argv[]) {
        String test1_Address[] = {"0xcf000","0x0017ddd5"};
        Atosl atosl = new Atosl();
        String[] a = atosl.findArch("./res/MapOut.app.dSYM/Contents/Resources/DWARF/MapOut");
        for(int i=0; i<7; i++) {
                System.out.println(a[i]);
        }
        atosl.symbolicate("armv7",
                          "./res/MapOut.app.dSYM/Contents/Resources/DWARF/MapOut",
                          test1_Address, test1_Address.length);
}
}
