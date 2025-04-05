#ifndef GAME_H_
#define GAME_H_
#include "commands.h"

void playGame(int* board, int size, int scoreToWin);
void printBoard(int*board, int size, int bestScore , int currentScore);
char printMenu();

#endif /* GAME_H_ */
