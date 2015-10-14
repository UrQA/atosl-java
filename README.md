# atosl-java
[atos](https://developer.apple.com/library/mac/documentation/Darwin/Reference/ManPages/man1/atos.1.html)**l**(for linux) for java

## Usage
### Requirements

* libelf
	* `sudo apt-get install libelf-dev`
* Iberty
	* Ubuntu 14.04 
		* `sudo apt-get install libiberty-dev` 
	* Ubuntu 12.04
		* `sudo apt-get install binutils-dev`
* libdwarf
	* Download from [Official Website](http://www.prevanders.net/dwarf.html)
	* Unzip source, then 
		* `./configure --enable-shared`
		* `make`
	* copy `libdwarf.so` file to `/usr/lib`
	* copy `libdwarf.h` and `dwarf.h` to **atosl-java** folder 

### Build

```
make
```

### Java
```
symbolicate(String arch, String executable, String[] addresses, int addressLength);
```

#### Return value
---

| code | status |
| --- | --- |
| 0 | success |
| -1 | unsupported architecture |
| -2 | unable to open dSYM file |
| -3 | unable to read dSYM file |
| -4 | unable to read architecture |
| -5 | unable to seek |
| -6 | architecture not found |
| -7 | invalid magic for architecture |
| -8 | invalid memory address |


## Example
* Example file is in `/test` folder
	* Data from [facebook/atosl | Issue #4](https://github.com/facebook/atosl/issues/4#issuecomment-36735953) 

## Reference
* [facebook/Atosl](https://github.com/facebook/atosl)
