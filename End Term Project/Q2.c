// Objective: Implementation of Banker’s algorithm to avoid deadlock
#include<stdio.h>
#define P 5 // number of processes 
#define R 4 // number of resources
int main(){
    int alloc[P][R] = {{0,0,1,2},{2,0,0,0},{0,0,3,4},{2,3,5,4},{0,3,3,2}};
    int max[P][R] = {{0,0,1,2},{2,7,5,0},{6,6,5,6},{4,3,5,6},{0,6,5,2}};
    int avail[R] = {0,1,0,4};
    int need[P][R];
    int finish[P] = {0};
    int safeSeq[P];
    // NEED MATRIX
    for(int i=0 ; i<P ; i++){
        for(int j=0 ; j<R ; j++){
            need[i][j] = max[i][j] - alloc[i][j];
        }
    }
    printf("Need Matrix:\n");
    for(int i=0 ; i<P ;i++){
        for(int j=0 ; j<R ;j++){
            printf("%d", need[i][j]);
        }
        printf("\n");
    }
    int count = 0;
    // Safety Algorithm 
    while(count < P){
        int found = 0;
        for(int i=0 ; i<P ;i++){
            if(!finish[i]){
                int j;
                for(j=0 ; j<R ;j++){
                    if(need[i][j] > avail[j]){
                        break;
                    }
                }
                // if all resources can be satisfied 
                if(j==R){
                    for(int k=0 ; k<R ; k++){
                        avail[k] += alloc[i][k];
                    }
                    safeSeq[count++] = i;
                    finish[i] = 1;
                    found = 1;
                }
            }
        }
        if(!found){
            break;
        }
    }
    // Result
    if(count == P){
        printf("\nSystem is in SAFE state.\nSafe Sequence: ");
        for(int i=0 ; i<P ; i++){
            printf("P%d", safeSeq[i]+1);
        }
    }else{
        printf("\nSystem is NOT in safe state.");
    }
    return 0;
}
