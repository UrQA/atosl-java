# atosl-java

[![volkswagen status](https://auchenberg.github.io/volkswagen/volkswargen_ci.svg?v=1)](https://github.com/auchenberg/volkswagen)

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
* addresses
	* addresses[0] : base address
	* addresses[1+] : runtime addresses

#### Return value
---
* findArch
	* return **String array**
		* array[0] : status code
		* array[1] : architecture count
		* array[2+] : architecture name
* symbolicate
	* return **String array**
		* array[0] : status code
		* array[1+] : symbolication result

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
| -9 | unable to read fat arch |


## Example
* Example file is in `/test` folder
	* Data from [facebook/atosl | Issue #4](https://github.com/facebook/atosl/issues/4#issuecomment-36735953) 

## Reference
* [facebook/Atosl](https://github.com/facebook/atosl)
* [5dolstory/Atosl](https://github.com/5dolstory/atosl)
	* [@ Slideshare](http://www.slideshare.net/ssuserff9480/ios-symbolication-53179936)
	* [@ brunch](https://brunch.co.kr/magazine/symbolication)
