#ifndef COMMANDS_H_
#define COMMANDS_H_

void newGame(int* board , int size,int* score);
int moveRight(int* board , int size,int* score);
int moveLeft(int* board , int size,int* score);
int moveUp(int* board , int size,int* score);
int moveDown(int* board , int size,int* score);
int endGame(int* board , int size, char* choose,int maxScore,int bestScore ,int* currentScore);
void addValue(int * board , int size);
int checkMovement(int* board , int size,int row , int col);


#endif /* COMMANDS_H_ */
