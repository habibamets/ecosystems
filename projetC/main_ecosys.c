#include <assert.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <strings.h>
#include "ecosys.h"



#define NB_PROIES 20
#define NB_PREDATEURS 20
#define T_WAIT 40000


/* Parametres globaux de l'ecosysteme (externes dans le ecosys.h)*/


int main(void) {

Animal*aproie = creer_animal(1,2,27.5);
 printf("la position avant deplacement de a est (%d,%d)",aproie->x,aproie->y);
bouger_animaux(aproie);
printf("la position apres deplacement de a est (%d,%d)",aproie->x,aproie->y); 



int i; 
for(i = 0; i<4;i++){

printf("le nombre d'animaux avant la  i = %d reproduction est %d",i,compte_animal_it(aproie));
reproduce(&aproie,1);
printf("\n");
printf("le nombre d'animaux apres la i = %d reproduction est %d\n",i,compte_animal_it(aproie));

}


Animal * liste_proie=NULL;
Animal *liste_predateur = NULL; 
int h;  
for( h = 0;h<NB_PROIES;h++){
  ajouter_animal(rand()%SIZE_X,rand()%SIZE_Y,rand()%10,&liste_proie);
  ajouter_animal(rand()%SIZE_X,rand()%SIZE_Y,rand()%12,&liste_predateur);
}
assert (liste_proie !=NULL); 
assert (liste_predateur !=NULL); 
 
int k,j; 
int monde[SIZE_X][SIZE_Y]; 
for(k = 0;k<SIZE_X;k++){
    for(j = 0;j<SIZE_Y;j++){
      monde[k][j] = 0; }
    }

int s=0; 

  while((s != 20)&& (compte_animal_it(liste_proie)> 0) && (compte_animal_it(liste_predateur)> 0)){
  rafraichir_monde(monde); 
  rafraichir_proies(&liste_proie,monde); 
  rafraichir_predateurs(&liste_predateur, &liste_proie);
  usleep(T_WAIT); 
  clear_screen(); 
  afficher_ecosys(liste_proie, liste_predateur);
  s++;
  printf("%d",s); 


}
  
  return 0;
}

