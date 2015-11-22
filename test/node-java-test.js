var java = require("java");
java.classpath.push("./src");

var Atosl = java.newInstanceSync("io.honeyqa.atosl.Atosl");

var testArch = Atosl.getArchSync("/Users/devholic/Desktop/git/honeyqa/atosl-java/test/res/m2048.app.dSYM/Contents/Resources/DWARF/m2048");
console.log(testArch.sizeSync());
