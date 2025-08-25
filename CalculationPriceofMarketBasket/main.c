#include <string.h>
#include <stdio.h>
#define N 2

double hesaplama = 0; // Total fee for all fruits

typedef struct meyve{
    char name[20];          // Fruit name
    float mass;             // Fruit weight
    char typeofFruit[10];   // Shape of the fruit
    int amountofFruit;      // Quantity
    float feeoffruit;       // Price per fruit
    struct sepet{
        double fee[N];      // Fee array to store individual fruit fees
    } massandform;
} fiyat;

// Calculate the fee for a single fruit and store it
double calculatedfee(fiyat *money, int i){
    if(strcmp(money->typeofFruit, "yuvarlak") == 0){
        money->feeoffruit *= 2; // Double price if shape is round
    }
    if (money->mass > 200){
        money->feeoffruit += 3; // Add 3 if weight > 200
    }

    money->feeoffruit *= money->amountofFruit; // Multiply by quantity

    money->massandform.fee[i] = money->feeoffruit; // Store fee in array
    hesaplama += money->feeoffruit;               // Add to total
    return money->massandform.fee[i];
}

// Take user input for a fruit and calculate its fee
void takeinput(fiyat *features, int i){
    printf("Enter fruit name: \n");
    scanf("%s", features->name);
    printf("Enter fruit mass: \n");
    scanf("%f", &features->mass);
    printf("Enter fruit shape: \n");
    scanf("%s", features->typeofFruit);
    printf("Enter amount of fruit: \n");
    scanf("%d", &features->amountofFruit);
    printf("Enter base fee of fruit: \n");
    scanf("%f", &features->feeoffruit);

    // Print entered info
    printf("Name: %s\n", features->name);
    printf("Mass: %.2f\n", features->mass);
    printf("Type of fruit: %s\n", features->typeofFruit);
    printf("Amount: %d\n", features->amountofFruit);
    printf("Fee per fruit: %.2f\n", features->feeoffruit);

    printf("Total fee for this fruit: %.2f\n", calculatedfee(features, i));
}

int main(int argc, const char * argv[]) {
    fiyat meyveler[N]; // Array of fruits

    for (int i=0; i<N; i++){
        takeinput(&meyveler[i], i); // Take input and calculate fee
    }

    printf("Total fee for all fruits -> %f\n", hesaplama);
}
