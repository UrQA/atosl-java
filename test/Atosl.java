public class Atosl {
native String getArchitecture(String dSYM);
native int symbolicate(String arch, String dSYM, String[] addresses, int addressLength);
static {
        System.load("atosl_library_path");
}
static public void main(String argv[]) {
        //self	ViewController *	0x1275442a0	0x00000001275442a0
        String test1_Address[] = {"0x0000b1e7"};
        String test2_Address[] = {"0x00006ed7"};
        Atosl atosl = new Atosl();
        atosl.symbolicate("armv7s", "./res/CrashTest3Dwarf.thin", test1_Address, test1_Address.length);
        atosl.symbolicate("armv7s", "./res/CrashTest3Dwarf.fat", test2_Address, test2_Address.length);
}
}
