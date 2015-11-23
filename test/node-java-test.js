var java = require("java");
java.classpath.push("./src");

var Atosl = java.newInstanceSync("io.honeyqa.atosl.Atosl");

var testArch = Atosl.getArchSync("./res/m2048.app.dSYM/Contents/Resources/DWARF/m2048");

for (var i = 0; i < testArch.sizeSync(); i++) {
    console.log(Atosl.getUUIDSync(testArch.getSync(i), "./res/m2048.app.dSYM/Contents/Resources/DWARF/m2048"));
}
