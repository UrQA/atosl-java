public class Atosl {
native int symbolicate(String arch, String dSYM, String[] addresses, int addressLength);
static {
        System.load("/Users/devholic/Desktop/git/honeyqa/atosl-java/libatosl.so");
}
static public void main(String argv[]) {
        //self	ViewController *	0x1275442a0	0x00000001275442a0
        String test1_Address[] = {"0x1275442a0","0x00000001275442a0"};
        Atosl atosl = new Atosl();
        System.out.println(atosl.symbolicate("arm64", "./test/res/test.app.dSYM/Contents/Resources/DWARF/test", test1_Address, test1_Address.length));
}
}
