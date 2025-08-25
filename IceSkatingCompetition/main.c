#include <stdio.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>

int check=1;

typedef struct buzpateni{
    char isimler[4][10];       // Competitors' names
    int juripuanalr[4][6];     // Scores from 6 judges for each competitor
    double performans[4];      // Calculated performance points
} yarismaci;

// Find and print the winner based on performance points
void findwinner(yarismaci *winner,int index){
    double max=winner->performans[0];
    index=0;
    for (int i=0; i<4; i++) {
        if(winner->performans[i]>max){
            max=winner->performans[i];
            index=i;
        }
    }
    printf("WINNER IS %s WITH %.2f POINTS\n",winner->isimler[index],winner->performans[index]);
}

// Calculate each competitor's performance after removing min and max judge scores
void printEVRYpoints(yarismaci *Sperformans,int line ){
    if(line<4){
        double perf=0;
        int min,max=Sperformans->juripuanalr[line][0];
        for (int j=0; j<6; j++) {
            if(Sperformans->juripuanalr[line][j]<min) min=Sperformans->juripuanalr[line][j];
            else max=Sperformans->juripuanalr[line][j];
            perf+=Sperformans->juripuanalr[line][j];
        }
        Sperformans->performans[line]=(perf-min-max)/4.0; // Average after removing min & max
        printf("%s's performance point is %.2f \n",Sperformans->isimler[line], Sperformans->performans[line]);
        printEVRYpoints(Sperformans, line+1);
    }else{
        printf("\n");
        findwinner(Sperformans,0); // After calculating all, find winner
    }
}

// Assign random points from judges to each competitor
void tookofpoints(yarismaci *puanları ,int line){
    if(line<4){
        for (int j = 0; j < 6; j++) {
            int random=(int)(rand()%10*6/9);
            puanları->juripuanalr[line][j]=random;
        }
        tookofpoints(puanları, line+1);
    }else{
        // Print raw judge points
        for (int i=0; i< 4; i++) {
            printf("%s's points-> ",puanları->isimler[i]);
            for (int j=0; j<6; j++) printf("%2d",puanları->juripuanalr[i][j]);
            printf("\n");
        }
        printf("\n");
        printEVRYpoints(puanları, 0); // Calculate performance points
    }
}

// Take competitor names as input
void inputname(yarismaci *isimleri,int line){
    if(line<4){
        printf("Please enter %d. competitor: ",line+1);
        char temp[20];
        scanf("%s",temp);
        strcpy(isimleri->isimler[line], temp); // Save name to struct
        inputname(isimleri, line+1);
    }else{
        printf("\n");
        tookofpoints(isimleri,0); // Assign random points after all names entered
    }
}

int main(int argc, const char * argv[]) {
    yarismaci yarışmacılar;
    srand(time(NULL));

    while(check!=0){
        printf("Hi, welcome to our dance competition\nIf you are ready, press any number\nOr press '0' to exit\n");
        scanf("%d",&check);
        if(check==0){
            printf("\nThank you so much for playing\n");
            check=0;
        }else
            inputname(&yarışmacılar,0); // Start competition
        printf("\n\n");
    }

    return 0;
}
