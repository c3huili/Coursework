

#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>

int main(int argc, char* argv[])
{
    DIR *mydir;
    struct dirent *myfile;
    struct stat mystat;
    mydir = opendir(argv[1]);
    printf("directory: %s\n", argv[1]);
    while((myfile = readdir(mydir)) != NULL)
    {
        stat(myfile->d_name, &mystat);
        printf("file size: %d", mystat.st_size);
        printf(" file name:  %s\n", myfile->d_name);
//        if(S_ISDIR(mystat.st_mode) && strcmp(myfile->d_name, ".") != 0 && strcmp(myfile ->d_name, "..") != 0)
//        {
//            printf("%s\n", strcat(argv[1], myfile->d_name));
//            mydir = opendir(strcat(argv[1], myfile->d_name));
//            printf("directory opened");
 //       }
    }
    printf("broke out of while loop");
    closedir(mydir);
    return 0;
}


