CC = gcc
OBJS = game.o main.o commands.o
CFLAGS = -Wall -Werror
EXEC = hw1_2048

$(EXEC) : $(OBJS)
	$(CC) $(OBJS) -o $@

commands.o: commands.c commands.h
game.o: game.c game.h commands.h
main.o: main.c game.h



clean:
	rm -f $(OBJS) $(EXEC)
