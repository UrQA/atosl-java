CC = gcc
CFLAGS = -Wall -c
SHAREDFLAGS = -shared
OFLAG = -o
LIB = libatosl.so
OBJECTS = atosl.o hqatosl.o converter.o

all: release

atosl.o: atosl.c hqatosl.h atosl.h
	$(CC) $(CFLAGS) atosl.c $(OFLAG) atosl.o

converter.o: converter.c converter.h
	$(CC) $(CFLAGS) converter.c $(OFLAG) converter.o

hqatosl.o: hqatosl.c hqatosl.h
	$(CC) $(CFLAGS) hqatosl.c $(OFLAG) hqatosl.o

release: CFLAGS += -O2
release: $(OBJECTS)
	$(CC) $(OFLAG) $(LIB) $(SHAREDFLAGS) $(OBJECTS)

clean:
	rm -rf $(LIB) $(DEBUG) *.o
