CC = gcc
CFLAGS = -Wall -c
LDFLAGS = -ldwarf -liberty
LDFLAGS += -L/usr/local/Cellar/binutils/2.24/lib/
SHAREDFLAGS = -shared
OFLAG = -o
LIB = libatosl.so
OBJECTS = atosl.o common.o subprograms.o

all: release

atosl.o: atosl.c atosl.h fbatosl.h subprograms.h common.h
	$(CC) $(CFLAGS) atosl.c $(OFLAG) atosl.o ${LDFLAGS}

common.o: common.c common.h
	$(CC) $(CFLAGS) common.c $(OFLAG) common.o ${LDFLAGS}

subprograms.o: subprograms.c subprograms.h
	$(CC) $(CFLAGS) subprograms.c $(OFLAG) subprograms.o ${LDFLAGS}

release: CFLAGS += -O2
release: $(OBJECTS)
	$(CC) $(OFLAG) $(LIB) $(SHAREDFLAGS) $(OBJECTS) ${LDFLAGS}

clean:
	rm -rf $(LIB) $(DEBUG) *.o
