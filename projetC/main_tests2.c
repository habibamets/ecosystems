#include <assert.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>


#include "ecosys.h"


int main (){
	int i;  
	float energie = 20; 
	Animal *liste_proie = NULL;
	Animal *liste_predateur = NULL;
	for (i=0; i<20; i++){
		ajouter_animal(0+(rand()%(SIZE_X)), 0+(rand()%(SIZE_Y)),energie, &liste_proie);
		ajouter_animal(0+(rand()%(SIZE_X)), 0+(rand()%(SIZE_Y+1)),energie,&liste_predateur);
		afficher_ecosys(liste_proie, liste_predateur); 
		}
	assert ( compte_animal_it(liste_proie)==20); 
	assert ( compte_animal_it(liste_predateur)==20); 
		printf("le nombre de predateur est %d\n",compte_animal_rec(liste_predateur));

	printf("le nombre de proie est %d\n", compte_animal_it(liste_proie));
	Animal * a = creer_animal(25,30,100.5); 
	liste_proie = ajouter_en_tete_animal(liste_proie, a); 
	printf("le nombre de proie est %d\n",compte_animal_rec(liste_proie));
	enlever_animal(&liste_proie, a);
	printf("le nombre de proie est %d\n",compte_animal_rec(liste_proie)); 
	

	ecrire_ecosys("test",liste_predateur,liste_proie);
	liste_proie = liberer_liste_animaux(liste_proie); 
	liste_predateur = liberer_liste_animaux(liste_predateur); 
 	assert(liste_proie == NULL); 
	assert(liste_predateur == NULL);
	
	lire_ecosys("test", &liste_predateur, &liste_proie); 
	ecrire_ecosys("test2",liste_predateur,liste_proie);
	liste_proie = liberer_liste_animaux(liste_proie); 
	liste_predateur = liberer_liste_animaux(liste_predateur); 
	assert(liste_proie == NULL); 
	assert(liste_predateur == NULL);



	return 0;
	}

