
 Ce document est réalisé afin de guider la correction du module TIC-MOBI.

 Login du chef de groupe : labego_s 
 Api utilisé : pokeAPI
 
 Tout d'abord, sur le dépots svn vous trouverez :
	- les points apk générés avec les deux diffent 'Build Variant' (debug et release) ne sachant pas lequel vous conviendrais le plus, nous avons décidez de laisser les deux.
	- le projet entier compresser.
	- et enfin, les fichier sources dans un dossier 'src' à part au cas où vous souhaiteriez y acceder rapidement.


Description rapide des vues
_____________________________________________________________________________________________________
Description de la premieres vue :

    Vous trouverez sur la première vue un menu composé d'imageButton permetant d'acceder à a la seconde vue.
    Les images utilisées sont des images que nous avons nous-même retouchées avec gimp. Ce qui nous a permis de faire cet effet de lumiere sur l'item sélectionner (clicker ou presser en continue).
    Il y a en tout 18 types de pokémons. Vous pouvez donc scroller et chercher un type précis.
    

_________________________________________________________________________________________________________
Description de la seconde vue :

   Comme demander dans le sujet, la seconde vue est une ListView. Cette liste est composer de tous les pokémons du type selectionnez dans la vue précédente.
   A l'aide de retrofit nous faisons une première requete ici pour obtenir les noms et les ids des pokémons. 
	url de requête : "http://pokeapi.co/api/v2/type/1/" 
   Le dernier numéro de l'url nous permet de récuperer la liste de type voulu. En revanche l'api ne fourni pas de photo nous avons donc du rajouter un autre système que retrofit pour inserer les images : Glide.

   Glide nous à permis de recuperer les image à partir d'une url, de les redimentionnez au 'contexte' voulu et de les insérer dans les espaces voulus.
   Vous pouvez obsterver le traitement du lien ci-dessous dans le fichier 'pokeAdaptateur.java' : 
	https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + type.getPokemon().get_nationalId() + ".png"

   nous concatenons cette url avec les id pokémons pour obtenir les images. 
   Le click d'un item redirige vers la derniere page.

____________________________________________________________________________________________________________
Page de Description d'un pokemon.

   Vous trouverez sur cette page le nom, la photo et la description du pokemon. Le texte dedescription est scrollable et bas de cette page vous trouverez les boutton de partage comme demander dans le sujet. Pour facebook et twitter, si les applis sont installées sur le tel alors elle s'ouvre sinon c'est un navigateur web qui s'ouvre.
La possibilité d'envoyer un message pré-rempli est aussi fonctionnelle. 
	la description et le choix de langue (voir bonus ci-dessous) est obtenu par l'url suivante : "http://pokeapi.co/api/v2/pokemon-species/{id}/" 

____________________________________________________________________________________________________
BONUS
____________________________________________________________________________________________________

Sur cette page de description, vous trouverez en haut à droite un menu qui vous permetra de choisir cette page en 8 langues différentes. Cela générera une popup qui annonce la langue selectionnez et settera le nom et la description dans la langues souhaitez.





________________________________________________________________________
Dépendance du projet.

 
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    testCompile 'junit:junit:4.12'

    compile 'com.squareup.retrofit2:retrofit:2.1.0'                   // Pour les requete à la pokeAPI
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'	      // pour le traiment json en objet java

    compile 'com.github.bumptech.glide:glide:3.7.0'                   // pour l'obtention des images pokemons
}










