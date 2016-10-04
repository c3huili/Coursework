#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>
#include <errno.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

pthread_mutex_t count_mutex;
pthread_t threads[10];


struct argument
{
    char pattern[256];
    int file_index;
    char file_name[256];
};

void search(char* pattern, int file_index, char* file_name);

void printMatch(int file_index, char* file_name, int lineNum, char* line)
{
    printf("%d %s %d %s", file_index, file_name, lineNum, line);
}

void readDir(char* pattern, int file_index, char* file_name)
{
    DIR* dir;
    dir = opendir(file_name);
    if(dir != NULL)
    {
        struct dirent *entry;
        while((entry = readdir(dir)))
        {
            if(strcmp(entry->d_name, ".") != 0 && strcmp (entry->d_name, "..") != 0)
            {
                char subDir[100];
                strcpy(subDir, file_name);
                strcat(subDir, "/");
                strcat(subDir, entry->d_name);
                search(pattern, file_index, subDir);
            }
        }
    }
    closedir(dir);
}

void *readFile(void* input)
{
    struct argument arg = *((struct argument*) input);
    FILE *ifp = fopen(arg.file_name, "r");
    if(ifp)
    {
        char* buffer;
        int n, lineNum = 0;
        size_t len = 0;
        while((n = getline(&buffer, &len, ifp)) != -1)
        {
            lineNum++;
            if(strstr(buffer, arg.pattern) != NULL)
            {
                printMatch(arg.file_index, arg.file_name, lineNum, buffer);
            }
        }
    }
}

void search(char* pattern, int file_index, char* file_name)
{
    struct stat mystat;
    if(stat(file_name, &mystat) == -1)
    {
        perror(file_name);
    }
    else
    {
        struct argument *arg = malloc(sizeof(struct argument));
        strcpy(arg->pattern, pattern);
        arg->file_index = file_index;
        strcpy(arg->file_name, file_name);
        if(S_ISDIR(mystat.st_mode))
        {
            readDir(pattern, file_index, file_name);
        }
        else if(S_ISREG(mystat.st_mode))
        {
            if(pthread_create(&threads[file_index], NULL, &readFile, (void*)arg))
            {
                perror("error in creating thread");
            }
//            if(pthread_join(threads[file_index], NULL))
//            {
//                perror("Joining error.");
//            }
            //pthread_create(&threads[file_index], NULL, readFile, &args);
//            readFile(pattern, file_index, file_name);
        }
    }
}

int main(int argc, char ** argv)
{
    int file_index = 1;
    if (argc < 3)
        perror("Too few arguments\n");
    else
    {
        for(int i = 2; i < argc; i++)
        {
            search(argv[1], file_index++, argv[i]);
        }
    }
    pthread_exit(NULL);
}
