CC = gcc
SHAREDFLAGS = -shared
OFLAG = -o
LIB = libatosl.so
OBJECTS = atosl.o hqatosl.o converter.o

all: release

atosl.o: Atosl.c hqatosl.h Atosl.h
	$(CC) atosl.c $(OFLAG) atosl.o

converter.o: converter.c converter.h
	$(CC) converter.c $(OFLAG) converter.o

hqatosl.o: hqatosl.c hqatosl.h
	$(CC) hqatosl.c $(OFLAG) hqatosl.o

release: CFLAGS += -O2
release: $(OBJECTS)
	$(CC) $(OFLAG) $(LIB) $(SHAREDFLAGS) $(OBJECTS)

clean:
	rm -rf $(LIB) $(DEBUG) *.o
