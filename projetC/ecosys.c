#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "ecosys.h"
#include <string.h>


float p_ch_dir = 0.01;
float p_reproduce_proie = 0.4;
float p_reproduce_predateur = 0.5;
int temps_repousse_herbe = -15;
/* PARTIE 1*/
/* Fourni: Part 1, exercice 3, question 2 */
Animal *creer_animal(int x, int y, float energie) {
  Animal *na = (Animal *)malloc(sizeof(Animal));
  assert(na);
  na->x = x;
  na->y = y;
  na->energie = energie;
  na->dir[0] = rand() % 3 - 1;
  na->dir[1] = rand() % 3 - 1;
  na->suivant = NULL;
  return na;
}


/* Fourni: Part 1, exercice 3, question 3 */
Animal *ajouter_en_tete_animal(Animal *liste, Animal *animal) {
  assert(animal);
  assert(!animal->suivant);
  animal->suivant = liste;
  return animal;
}

/* A faire. Part 1, exercice 5, question 1 */
void ajouter_animal(int x, int y,  float energie, Animal **liste_animal) {
assert(x>=0 && x<=SIZE_X); 
assert(y>=0 && y<=SIZE_Y); 
  Animal *a = creer_animal(x,y,energie);
 *liste_animal = ajouter_en_tete_animal(*liste_animal, a); 

}

/* A Faire. Part 1, exercice 5, question 5 */
void enlever_animal(Animal **liste, Animal *animal) {
	Animal*p = *liste;

  Animal*prec = NULL;
  Animal*tmp = NULL;

  while(p){
    if ((p->x == animal->x)&&(p->y == animal->y)&&(p->energie ==animal->energie)){
        tmp = p;

        p = p->suivant;
        free(tmp);

  if (prec != NULL){
     
      prec->suivant = p;
    }
    else{
      *liste = p;
      

    }
    return;
  }
  else{
  prec = p;
  p = p->suivant;
}

  }
  

  return ;
}

/* A Faire. Part 1, exercice 5, question 2 */
Animal* liberer_liste_animaux(Animal *liste) {
   Animal* tmp;
while(liste){
	
	tmp = liste;
	liste = liste->suivant; 
	free(tmp); 

	 }

  return liste;
}

/* Fourni: part 1, exercice 3, question 4 */
unsigned int compte_animal_rec(Animal *la) {
  if (!la) return 0;
  return 1 + compte_animal_rec(la->suivant);
}

/* Fourni: part 1, exercice 3, question 4 */
unsigned int compte_animal_it(Animal *la) {
  int cpt=0;
  Animal*p = la;
  while (p) {
    cpt++;
    p=p->suivant;
  }
  return cpt;
}



/* Part 1. Exercice 4, question 1, ATTENTION, ce code est susceptible de contenir des erreurs... */
void afficher_ecosys(Animal *liste_proie, Animal *liste_predateur) {
  unsigned int i, j;
  char ecosys[SIZE_X][SIZE_Y];
  Animal *pa=NULL;

  /* on initialise le tableau */
  for (i = 0; i < SIZE_X; ++i) {
    for (j = 0; j < SIZE_Y; ++j) {
      ecosys[i][j]=' ';
    }
  }

  /* on ajoute les proies */
  pa = liste_proie;
  while (pa) {
    ecosys[pa->x][pa->y] = '*';
    pa=pa->suivant;
  }

  /* on ajoute les predateurs */
  pa = liste_predateur;
  while (pa) {
      if ((ecosys[pa->x][pa->y] == '@') || (ecosys[pa->x][pa->y] == '*')) { /* proies aussi present */
        ecosys[pa->x][pa->y] = '@';
      } else {
        ecosys[pa->x][pa->y] = 'O';
      }
    pa = pa->suivant;
  }

  /* on affiche le tableau */
  printf("+");
  for (j = 0; j < SIZE_Y; ++j) {
    printf("-");
  }  
  printf("+\n");
  for (i = 0; i < SIZE_X; ++i) {
    printf("|");
    for (j = 0; j < SIZE_Y; ++j) {
      putchar(ecosys[i][j]);
    }
    printf("|\n");
  }
  printf("+");
  for (j = 0; j<SIZE_Y; ++j) {
    printf("-");
  }
  printf("+\n");
  int nbproie=compte_animal_it(liste_proie);
  int nbpred=compte_animal_it(liste_predateur);
  
  printf("Nb proies : %5d\tNb predateurs : %5d\n", nbproie, nbpred);

}


void clear_screen() {
  printf("\x1b[2J\x1b[1;1H");  /* code ANSI X3.4 pour effacer l'ecran */
}

/* PARTIE 2*/

/* Part 2. Exercice 4, question 1 */
void bouger_animaux(Animal *la) {
    Animal * ap = la;
    while(ap){
      if(rand()/(float)RAND_MAX < p_ch_dir){
        ap->dir[0] = rand()%3 -1 ; 
        ap->dir[1] = rand()%3-1; 
      }
      ap->x = (ap->x + ap->dir[0] + SIZE_X)%SIZE_X; 
      ap->y = (ap->y + ap->dir[0] + SIZE_Y)%SIZE_Y; 
      ap = ap->suivant; 
    }
  
}

/* Part 2. Exercice 4, question 3 */
void reproduce(Animal **liste_animal, float p_reproduce) {
   Animal * ap = NULL;
   if(liste_animal){
    ap = *liste_animal; 
   }
   while(ap){

    if(rand()/(float)RAND_MAX < p_reproduce){
      ajouter_animal(ap->x,ap->y,(ap->energie)/2.0,liste_animal); 
      ap->energie = (ap->energie)/2.0; 

   }

   ap = ap->suivant; }

}


/* Part 2. Exercice 6, question 1 */
void rafraichir_proies(Animal **liste_proie, int monde[SIZE_X][SIZE_Y]) {
  rafraichir_monde(monde); 
  Animal*p = *liste_proie;
    bouger_animaux(p);
    while(p){
      p->energie = p->energie -1;
      if(monde[p->x][p->y] > 0 ){
        p->energie = p->energie + monde[p->x][p->y]; 
        monde[p->x][p->y] = temps_repousse_herbe; 
      }
      if (p->energie <=0){
        enlever_animal(liste_proie,p);
      }
      p = p->suivant;
      }
      reproduce(liste_proie,p_reproduce_proie);

    }




/* Part 2. Exercice 7, question 1 */
Animal *animal_en_XY(Animal *l, int x, int y) {
    Animal*ap = l;
    while(ap){
      if((ap->x == x) && (ap->y == y)){
        return ap;
      }
      ap = ap->suivant;
    }

  return NULL;
} 

/* Part 2. Exercice 7, question 2 */
void rafraichir_predateurs(Animal **liste_predateur, Animal **liste_proie) {

   Animal*p = *liste_predateur;
    bouger_animaux(p);
    while(p){
      p->energie = p->energie - 1.0;
      if (p->energie <=0.0){
        enlever_animal(liste_predateur,p);
      }
      Animal * a = animal_en_XY(*liste_proie,p->x,p->y); 
      if(a!=NULL){
        p->energie = p->energie + a->energie; 
        enlever_animal(liste_proie,a);

      }
      p = p->suivant;  
      }
      reproduce(liste_predateur,p_reproduce_predateur);

}

/* Part 2. Exercice 5, question 2 */
void rafraichir_monde(int monde[SIZE_X][SIZE_Y]){
int i,j; 
  for( i = 0;i<SIZE_X;i++){
    for(j = 0;j<SIZE_Y;j++){
      monde[i][j] = (monde[i][j])+1;
    }
  }

   


}
void ecrire_ecosys(const char *nom_fichier, Animal *liste_predateur, Animal *liste_proie){
			FILE* f = fopen(nom_fichier,"w");
			fprintf(f,"<proies>\n");
			Animal*tmp = liste_proie;
			while(tmp){
				fprintf(f,"x = %d, y = %d,dir = [%d %d],e = %.6f\n",tmp->x,tmp->y,tmp->dir[0],tmp->dir[1],tmp->energie);
				tmp = tmp->suivant;
			
			}
			fprintf(f,"</proies>\n");
			
			fprintf(f,"<predateurs>\n");
				 tmp = liste_predateur;
			while(tmp){
				fprintf(f,"x = %d, y = %d,dir = [%d %d],e = %.6f\n",tmp->x,tmp->y,tmp->dir[0],tmp->dir[1],tmp->energie);
				tmp = tmp->suivant;
			
			}
			fprintf(f,"</predateurs>\n");
			fclose(f);
			
			
			
			
			}
			
		void lire_ecosys(const char *nom_fichier, Animal **liste_predateur, Animal **liste_proie){
		FILE*f = fopen(nom_fichier,"r");
    char buffer[256];
    fgets(buffer,256,f);
    assert(strncmp(buffer,"<proies>",8) == 0);
    fgets(buffer,256,f);
    int xl,yl,dirl[2];
    float el;
    while(strncmp(buffer,"</proies>",9)!= 0){
      sscanf(buffer, "x=%d y=%d dir=[%d %d] e=%f\n", &xl, &yl, &dirl[0], &dirl[1], &el); 
      Animal *al = creer_animal(xl, yl, el); 
      al->dir[0] = dirl[0]; 
      al->dir[1] = dirl[1]; 
      al->suivant = *liste_proie; 
      *liste_proie = al; 
      fgets(buffer, 256,f); 
    }
    fgets(buffer, 256,f); 
    assert(strncmp(buffer,"<predateurs>",12) == 0);
    fgets(buffer,256,f);
    while(strncmp(buffer,"</predateurs>",13)!= 0){
      sscanf(buffer, "x=%d y=%d dir=[%d %d] e=%f\n", &xl, &yl, &dirl[0], &dirl[1], &el); 
      Animal *ab = creer_animal(xl, yl, el); 
      ab->dir[0] = dirl[0]; 
      ab->dir[1] = dirl[1]; 
      ab->suivant = *liste_predateur; 
      *liste_predateur = ab; 
      fgets(buffer, 256,f); 
    }
	fclose(f);


}

			
			
			
			
			
			
			
			
			


