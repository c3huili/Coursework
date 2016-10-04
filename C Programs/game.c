#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

#define col 88
#define row 30

typedef struct{ char alive; }cell;

cell world[col][row], newWorld[col][row];

int neighbors(int i, int j);

void update()
{
    for(int i = 0; i < row; i++)
    {
        for(int j = 0; j < col; j++)
        {
            int n = neighbors(j, i);
            if((world[j][i].alive == '1') && n < 2)   // live cell with 1 or 4+ neighbors dies
                newWorld[j][i].alive = '0';
            if(world[j][i].alive == '1' && n > 3)
                newWorld[j][i].alive = '0';
            else if((world[j][i].alive == '0') && (n == 3)) // dead cell with 3 neighbors comes to life
                newWorld[j][i].alive = '1';
            else if(world[j][i].alive == '1' && n == 2 || n == 3)
                newWorld[j][i].alive = '1';
        }
    }
}

int neighbors(int i, int j)
{
    int count = 0;

    if(world[j-1][i-1].alive == '1') count++;
    if(world[j][i-1].alive == '1') count++;
    if(world[j+1][i-1].alive == '1') count++;
    if(world[j-1][i].alive == '1') count++;
    if(world[j][i].alive == '1') count++;
    if(world[j+1][i].alive == '1') count++;
    if(world[j-1][i+1].alive == '1') count++;
    if(world[j][i+1].alive == '1') count++;
    if(world[j+1][i+1].alive == '1') count++;

    return count;
}

void printWorld()
{
    for(int i = 0; i < row; i++)
    {
        for(int j = 0; j < col; j++)
        {
            if(newWorld[j][i].alive == '0')
                putchar(' ');
            else if(newWorld[j][i].alive == '1')
                putchar('*');
        }
        printf("\n");
    }
}

void updateWorld()
{
    for(int i = 0; i < row; i++)
    {
        for(int j = 0; j < col; j++)
        {
            world[j][i] = newWorld[j][i];
        }
    }
}


void start()
{
    srand(time(NULL));
    for(int i = 1; i < row - 1; i ++)
    {
        for(int j = 0; j < col - 1; j++)
        {
            world[j][i].alive = rand() % 2 == 1 ? '1': '0';
        }
    }
}

int main()
{
    system("clear");
    start();
    while(1){
        printWorld();
        update();
        updateWorld();
//        usleep(25000);
        sleep(1);
        system("clear");
    }
    return 0;
}
