#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include "commands.h"
#include "game.h"
#include <stdlib.h>

void newGame(int * board , int size,int* score)
{
	*score = 0;
	for (int i = 0; i < size; i++) {
        	for (int j = 0; j < size; j++) {
           	   *(board + i *size  + j) = 0;  // reset the cells to 0
        	}
    	}    	
}
int moveRight(int* board , int size,int* score)
{
	int i,j,lastplace;
	int flagmove = 0;
	for ( i = 0; i < size; i++) 
	{
		lastplace = size - 1;
        	for ( j = size - 1 ; j >= 0; j--) 
        	{
        		if(*(board + i *size  + j) != 0)
        		{
        			*(board + i *size  + lastplace)	= *(board + i *size  + j);
        			if(lastplace != j)
        			{
        				*(board + i *size  + j) = 0;
        				flagmove = 1;			
        			}
        			lastplace--;	
        		}
        			
        	}
        		
        }
        for ( i = 0; i < size; i++) 
	{
        	for ( j = size - 1 ; j > 0; j--) 
        	{
        		if (*(board + i *size  + j) == *(board + i *size  + j - 1))
        		{
        			if (*(board + i *size  + j)!= 0)
        			{
        				flagmove = 1;
        			}
        			*(board + i *size  + j)	= *(board + i *size  + j) * 2;
        			*(board + i *size  + j - 1) = 0;
        			*score += *(board + i *size  + j);
        			for(int k = j - 1; k > 0; k--)
        			{
        				*(board + i *size  + k) = *(board + i *size  + k - 1);	
        			}
        			*(board + i * size) = 0;
        			break;
        			
        		}
        	}
        		
        }
        if(flagmove == 0)
			{
				printf("Nothing to move in this direction,choose another option\n");
				
			}
			else
			{
				addValue(board , size);
				
			}
        
        return flagmove;
        
}

int moveLeft(int* board , int size,int* score)
{
	int i,j,lastplace;
	int flagmove = 0;
	for ( i = 0; i < size; i++) 
	{
		lastplace = 0;
        	for ( j = 0 ; j < size; j++) 
        	{
        		if(*(board + i *size  + j) != 0)
        		{
        			*(board + i *size  + lastplace)	= *(board + i *size  + j);
        			if(lastplace != j)
        			{
        				*(board + i *size  + j) = 0;
        				flagmove = 1;	
        			}
        			lastplace++;	
        		}	
        	}	
        }
        
        for ( i = 0; i < size; i++) 
	{
        	for ( j = 0 ; j < size - 1; j++) 
        	{
        		if (*(board + i *size  + j) == *(board + i *size  + j + 1))
        		{
        			if (*(board + i *size  + j)!= 0)
        			{
        				flagmove = 1;
        			}
        			*(board + i *size  + j)	= *(board + i *size  + j) * 2;
        			*(board + i *size  + j + 1) = 0;
        			*score += *(board + i *size  + j);
        			for(int k = j + 1; k < size - 1; k++)
        			{
        				*(board + i *size  + k) = *(board + i *size  + k + 1);	
        			}
        			*(board + i * size + size - 1) = 0;
        			break;
        			
        			
        		}
        	}	
        }
        if(flagmove == 0)
		{
			printf("Nothing to move in this direction,choose another option\n");
			
		}
	else
		{
			addValue(board , size);
			
		}
        return flagmove;
        
}
int moveUp(int* board , int size,int* score)
{
	int i,j,lastplace;
	int flagmove = 0;
	for ( j = size - 1; j >=0 ; j--) 
	{
		lastplace = 0;
        	for ( i = 0 ; i < size; i++) 
        	{
        		if(*(board + i *size  + j) != 0)
        		{
        			*(board + lastplace *size  + j)	= *(board + i *size  + j);
        			if(lastplace != i)
        			{
        				*(board + i *size  + j) = 0;
        				flagmove = 1;	
        			}
        			lastplace++;	
        		}
        			
        	}	
        }
        for ( j = size - 1; j >=0 ; j--) 
	{
        	for ( i = 0 ; i < size -1; i++) 
        	{
        		if (*(board + i *size  + j) == *(board + (i + 1) *size  + j))
        		{
        			if (*(board + i *size  + j)!= 0)
        			{
        				flagmove = 1;
        			}
        			*(board + i *size  + j)	= *(board + i *size  + j) * 2;
        			*(board + (i + 1) *size  + j ) = 0;
        			*score += *(board + i *size  + j);
        			for(int k = i + 1; k < size - 1; k++)
        			{
        				*(board + k *size  + j) = *(board +((k + 1) *size)  + j);	
        			}
        			*(board + (size - 1) * size + j) = 0;
        			break;
        			
        			
        		}
        	}	
        }
        if(flagmove == 0)
		{
			printf("Nothing to move in this direction,choose another option\n");
			
		}
	else
		{
			addValue(board , size);
			
		}
        return flagmove;
}
int moveDown(int* board , int size,int* score)
{
	int i,j,lastplace;
	int flagmove = 0;
	for ( j = 0; j < size; j++) 
	{
		lastplace = size - 1;
        	for (i = size - 1; i >= 0; i--) 
        	{
        		if(*(board + i *size  + j) != 0)
        		{
        			*(board + lastplace *size  + j)	= *(board + i *size  + j);
        			if(lastplace != i)
        			{
        				*(board + i *size  + j) = 0;	
        				flagmove = 1;
        			}
        			lastplace--;	
        		}	
        	}	
        }
        
        for ( j = 0; j < size; j++) 
	{
        	for (i = size - 1; i >= 0; i--)
        	{
        		if (*(board + i *size  + j) == *(board + (i - 1) *size  + j))
        		{
				if (*(board + i *size  + j)!= 0)
				{
					flagmove = 1;
				}
        			*(board + i *size  + j)	= *(board + i *size  + j) * 2;
        			*(board + (i - 1) *size  + j ) = 0;
        			*score += *(board + i *size  + j);
        			for(int k = i - 1; k > 0; k--)
        			{
        				*(board + k *size  + j) = *(board +((k - 1) *size)  + j);	
        			}
        			*(board + 0 * size + j) = 0;
        			break;
        		}
        	}	
        	
        }
        if(flagmove == 0)
	{
		printf("Nothing to move in this direction,choose another option\n");
		
	}
	else
	{
		addValue(board , size);
		
	}
        return flagmove;
}	
int endGame(int* board , int size, char* choose,int scoreToWin, int bestScore ,int* currentScore)
{
	if (*choose == 'e' || *choose == 'E')
	{
		printf("Ending previous game - your score %d best score %d \n",*currentScore,bestScore);
		return 0;
	}
	for (int i = 0; i < size; i++) 
	{
        	for (int j = 0; j < size; j++) 
        	{
			 if(*(board + i *size  + j) == scoreToWin)
		   	 {
		   	 	printf("You Won %d ! - ending game \n",scoreToWin);	 
				*currentScore = 0;
				newGame(board , size,currentScore);
		   	 	*choose = printMenu(); 
		   	 	return 1;
		   	 }	
		 
           	}
          }
         
          for (int i = 0; i < size; i++) 
	  {
        	for (int j = 0; j < size; j++) 
        	{
			 if (*(board + i *size  + j) == 0)
			 {
			 	return 1;
			 }
		}
	}
	for (int i = 0; i < size-1; i++) 
	  {
        	for (int j = 0; j < size-1; j++) 
        	{
        		if (*(board + i *size  + j)==*(board + i *size  + j+1) ||*(board + i *size  + j)==*(board + (i+1) *size  + j))
        			{
        				return 1;
        			}        	
		}
	  }
	
	printf("Ending previous game - your score %d best score %d \n",*currentScore,bestScore);
	return 0;

}


void addValue(int * board , int size)
{
	int random = rand() % (size*size);
	int random2;
	while(*(board + random))
	{
		random = rand() % (size*size-1);
	}
	random2 = rand() % 10;
	if (random2<7)
	{
		*(board + random) = 2;
	}
	else 
	{
		*(board + random) = 4;	
	}
}

	














