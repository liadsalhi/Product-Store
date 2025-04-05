#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include "game.h"


//#define SIZE 4

void playGame(int*board, int size, int scoreToWin)
{	
	char choose;
	int flagmove = 0, flagToWin = 1;
	int currentScore =0 , bestScore=0;
	newGame(board , size,&currentScore);
    	printBoard(board, size,bestScore,currentScore);
	choose = printMenu();
	while(choose != 'n' && choose != 'N'&& choose != 'e' && choose != 'E')
	{
		
		printf("Need to start game first \n\n\n");
		printBoard(board, size,bestScore,currentScore);
		choose = printMenu();
	}	
	while (flagToWin != 0)
	{  	
		if (choose == 'e' || choose == 'E')
		{
			printf("Ending previous game - your score %d best score %d \n",currentScore,bestScore);
			break;
		}
		else if (choose == 'n' || choose == 'N')
		{	
			newGame(board , size,&currentScore);
			addValue(board , size);
			addValue(board , size);
		}
		else if (choose == 'r' || choose == 'R')
		{
			flagmove = moveRight(board , size,&currentScore);
		}
		else if (choose == 'l' || choose == 'L')
		{
			flagmove = moveLeft(board , size,&currentScore);
		}
		else if (choose == 'u' || choose == 'U')
		{
			flagmove = moveUp(board , size,&currentScore);	
		}
		else if (choose == 'd' || choose == 'D')
		{
			flagmove = moveDown(board , size,&currentScore);
		}
		else
		{
			printf("wrong option   \n");
		}
		if (currentScore > bestScore)
		{
			bestScore =currentScore; 
		}
		printf("Score : %d  Best : %d   \n",currentScore , bestScore);
		printBoard(board, size,bestScore,currentScore);
		flagToWin =endGame(board ,size, &choose, scoreToWin,bestScore ,&currentScore);
		choose = printMenu();
		printf("%d", flagmove);
	}
		
}

char printMenu()
{
	char choose;
	printf("Please choose one of the following options : \n");
	printf("N/n - new game \n");
	printf("R/r - move right \n");
	printf("L/l - move left \n");
	printf("U/u - move up \n");
	printf("D/d - move down \n");
	printf("E/e - exit \n");
	scanf("%c", &choose);
	getchar();
	return choose;
}

void printBoard(int* board, int size, int bestScore , int currentScore)
{
    for (int i = 0; i < size; i++) {
      
        for (int j = 0; j < size; j++) {
            printf("------"); 
        }
        printf("-\n"); 
        
       
        for (int j = 0; j < size; j++) {
            if (*(board + i * size + j))
            {
            	 printf("|  %d  ", *(board + i *size  + j)); // cell with value
            }
            else 
            	printf("|     "); // empty cell
        }
        printf("|\n");  // end of the line
    }

    for (int j = 0; j < size ; j++) {
        printf("------");  
    }
    printf("-\n");  

}









