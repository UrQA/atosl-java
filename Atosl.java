public class Atosl {
native int symbolicate(String arch, String dSYM, String[] addresses, int addressLength);
static {
        System.load("/Users/devholic/Desktop/git/honeyqa/atosl-java/libatosl.so");
}
static public void main(String argv[]) {
        // self	ViewController *	0x7fcd88495d40	0x00007fcd88495d40
        // sender	UIButton *	0x7fcd8a001d20	0x00007fcd8a001d20
        String test1_Address[] = {"0xcf000","0x0017ddd5"};
        Atosl atosl = new Atosl();
        System.out.println(atosl.symbolicate("armv7", "./test/res/MapOut.app.dSYM/Contents/Resources/DWARF/MapOut", test1_Address, test1_Address.length));
        }
}
