#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>

int edge;          // Sudoku size (NxN)
int condition = 1; // Loop control for main menu

// Print the Sudoku array recursively
void print(int arr[][edge], int line, int len){
    if(line < len){
        for (int j=0; j<len; j++){
            if(j==0) printf("%d",arr[line][j]);
            else printf("%8d",arr[line][j]);
        }
        printf("\n\n");
        print(arr, line+1, len);
    }
}

// Randomly shuffle rows of the array to create variation
void Tomixarray(int arr[][edge], int temparr[][edge], int line, int len){
    int random = rand() % len;
    if(line < len){
        for (int j=0; j<len; j++){
            temparr[line][j] = arr[line][j];   // Temporary backup
            arr[line][j] = arr[random][j];     // Swap with random row
            arr[random][j] = temparr[line][j];
        }
        Tomixarray(arr, temparr, line+1, len);
    } else {
        print(arr, 0, len); // Print final shuffled array
    }
}

// Fill the Sudoku array with a base pattern (cyclic shifting)
void fillarray(int arr[][edge], int line){
    int len = edge;
    int start = len;
    if(line < edge){
        if(line > 0) start = arr[line-1][0];
        for (int j=0; j<len-1; j++){
            if(line==0) arr[line][j] = j+1;
            else arr[line][j] = arr[line-1][j+1];
        }
        arr[line][len-1] = start;
        fillarray(arr, line+1);
    } else {
        int temparr[edge][edge];
        Tomixarray(arr, temparr, 0, len); // Shuffle rows for variation
    }
}

// Take user input for Sudoku size or exit
void input(){
    printf("Please enter height-width of sudoku or exit(0)\n");
    scanf("%d",&edge);
    if(edge > 0){
        int sudoku[edge][edge];
        fillarray(sudoku, 0); // Fill Sudoku
    } else if(edge < 0){
        printf("Please enter a positive number\n");
    } else {
        printf("Thank you for playing\n");
        condition = 0;
    }
}

int main(int argc, const char * argv[]) {
    srand(time(NULL));
    while(condition == 1){
        input(); // Ask for size and generate Sudoku
    }
    return 0;
}
