#include <stdio.h>
#include <string.h>

void printMorse(char c)
{
    char ch = toupper(c);
    switch(ch)
    {
    case 'A': printf(". -   "); break;
    case 'B': printf("- . . .   "); break;
    case 'C': printf("- . - .   "); break;
    case 'D': printf("- . .   "); break;
    case 'E': printf(".   "); break;
    case 'F': printf(". . - .   "); break;
    case 'G': printf("- - .   "); break;
    case 'H': printf(". . . .   "); break;
    case 'I': printf(". .   "); break;
    case 'J': printf(". - - -   "); break;
    case 'K': printf("- . -   "); break;
    case 'L': printf(". - . .   "); break;
    case 'M': printf("- -   "); break;
    case 'N': printf("- .   "); break;
    case 'O': printf("- - -   "); break;
    case 'P': printf(". - - .   "); break;
    case 'Q': printf("- - . -   "); break;
    case 'R': printf(". - .   "); break;
    case 'S': printf(". . .   "); break;
    case 'T': printf("-   "); break;
    case 'U': printf(". . -   "); break;
    case 'V': printf(". . . -   "); break;
    case 'W': printf(". - -   "); break;
    case 'X': printf("- . . -   "); break;
    case 'Y': printf("- . - -   "); break;
    case 'Z': printf("- - . .   "); break;
    case '0': printf("- - - - -   "); break;
    case '1': printf(". - - - -   "); break;
    case '2': printf(". . - - -   "); break;
    case '3': printf(". . . - -   "); break;
    case '4': printf(". . . . -   "); break;
    case '5': printf(". . . . .   "); break;
    case '6': printf("- . . . .   "); break;
    case '7': printf("- - . . .   "); break;
    case '8': printf("- - - . .   "); break;
    case '9': printf("- - - - .   "); break;
    case ' ': printf("       "); break;
    default: printf(""); break;
    }
}

char isNum(char* str)
{
    if (strcmp(str, ". - - - -") == 0) return '1';
    if (strcmp(str, ". . - - -") == 0) return '2';
    if (strcmp(str, ". . . - -") == 0) return '3';
    if (strcmp(str, ". . . . -") == 0) return '4';
    if (strcmp(str, ". . . . .") == 0) return '5';
    if (strcmp(str, "- . . . .") == 0) return '6';
    if (strcmp(str, "- - . . .") == 0) return '7';
    if (strcmp(str, "- - - . .") == 0) return '8';
    if (strcmp(str, "- - - - .") == 0) return '9';
    if (strcmp(str, "- - - - -") == 0) return '0';
    return '~';
}

char isLetter(char* str)
{
    if (strcmp(str, ".-") == 0) return 'A';
    if (strcmp(str, "-...") == 0) return 'B';
    if (strcmp(str, "-.-.") == 0) return 'C';
    if (strcmp(str, "-..") == 0) return 'D';
    if (strcmp(str, ".") == 0) return 'E';
    if (strcmp(str, "..-.") == 0) return 'F';
    if (strcmp(str, "--.") == 0) return 'G';
    if (strcmp(str, "....") == 0) return 'H';
    if (strcmp(str, "..") == 0) return 'I';
    if (strcmp(str, ".---") == 0) return 'J';
    if (strcmp(str, "-.-") == 0) return 'K';
    if (strcmp(str, ".-..") == 0) return 'L';
    if (strcmp(str, "--") == 0) return 'M';
    if (strcmp(str, "-.") == 0) return 'N';
    if (strcmp(str, "---") == 0) return 'O';
    if (strcmp(str, ".--.") == 0) return 'P';
    if (strcmp(str, "--.-") == 0) return 'Q';
    if (strcmp(str, ".-.") == 0) return 'R';
    if (strcmp(str, "...") == 0) return 'S';
    if (strcmp(str, "-") == 0) return 'T';
    if (strcmp(str, "..-") == 0) return 'U';
    if (strcmp(str, "...-") == 0) return 'V';
    if (strcmp(str, ".--") == 0) return 'W';
    if (strcmp(str, "-..-") == 0) return 'X';
    if (strcmp(str, "-.--") == 0) return 'Y';
    if (strcmp(str, "--..") == 0) return 'Z';
    else  printf("       ");
    return '~';
}

void printAlpha(char* str)
{
    char buffer[1000], tmp[1000], *ch;
    int flag = 0, i = 2;
    strcpy(tmp, str);
    
    ch = strtok(tmp, " ");
    strcpy(buffer, ch);
    while(ch != NULL)
    {
        if(tmp[i] == ' ' && strlen(buffer) < 5)
        {
        printf("buffer: %s\n", buffer);
            printf("%c", isLetter(buffer));
            memset(buffer, '\0', 1000);
            flag = 1;
        }
        else if(strlen(buffer) < 5) i+=2;
        else 
        {i += 3;}
//        i++;
        ch = strtok(NULL, " ");
        strcat(buffer, ch);
    }

}

int main()
{
    char str[1000];
    do
    {
        gets(str);
        for(int i = 0; i < strlen(str); i++)
            printMorse(str[i]);
        printf("\n");
    }while (strcmp(str, "q") != 0);
    printf("\n");
    char* hello = ". . . .   .   . - . .   . - . .   - - -";
    char* h = ". . . .   ";
    printAlpha(hello);
    return 0;

}
