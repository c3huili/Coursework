#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <errno.h>

void wc(char* str);

void readDir(char *directory)
{
    DIR* dir;
    dir = opendir(directory);
    printf("Directory: %s\n", directory);
    if(dir != NULL)
    {
        struct dirent *d;
        while((d = readdir(dir)))
        {
            if(strcmp(d->d_name, ".") != 0 && strcmp(d->d_name, "..")!= 0)
            {
                char n[100];
                strcpy(n, directory);
                strcat(n, "/");
                strcat(n, d->d_name);
                wc(n);
                //wc(newPath(directory, entry->d_name));
            }
        }
    }
    closedir(dir);
}

void readFile(char* fileName)
{
    int wordCount = 0, characterCount = 0, lineCount = 0;
    FILE *ifp = fopen(fileName, "r");
//    printf("\tfile: %s\n", fileName);
    if(ifp)
    {
        int c = fgetc(ifp);
        while(c != EOF)
        {
            if(isspace(c))
            {
                wordCount++;
            }
            if(c == '\0' || c == '\n')
            {
                lineCount++;
                wordCount++;
            }
            characterCount++;
            c = fgetc(ifp);
        }
    }
    fclose(ifp);
    printf("ch: %d wd: %d ln: %d path: %s\n", characterCount, wordCount, lineCount, fileName);
}
/*
char* newPath(char* directory, char* path)
{
    char newP[100];
    strcpy(newP, directory);
    if(strchr(directory, "/") == NULL)
        strcat(newP, "/");
    strcat(newP, path);
    return newP;
    
}
*/


void wc(char *str)
{
    struct stat s;
    if(stat(str, &s) == -1)
    {
        perror(str);
    }
    else
    {
        if(S_ISDIR(s.st_mode))
            readDir(str);
        else if(S_ISREG(s.st_mode))
            readFile(str);
    }

}

int main(int argc, char* argv[])
{
    for(int i = 0; i < argc; i++)
    {
        wc(argv[1]);
    }
    return 0;
}


