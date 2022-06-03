
# {Weather-App}  

A desktop weather app.  

```
$> gradle run

or 

$> gradle runCLI
```

## INTRODUCTION

Sujet ->  [Cahier des charges](/cahier_charges.pdf)

Application météo écrite en java. <br>


## Application graphique

L'application graphique repose sur l'utilisation de la librairie *javaFX*.<br>
L'application propose, en fonction de la ville sélectionnée :
- La météo actuelle 
- la météo sur 5 jours
- la météo heure par heure sur 12h
- une localisation sur une map 2D
- une courbe de la température à prevoir sur 12h

L'application propose aussi une gestion de favoris pour un total de 6 villes maximum.
Ces favoris s'enregistrent même si l'application est fermée.

## Application en ligne de commande

L'application en ligne de commande propose 3 commandes pour chercher une ville par:
- son nom
- son ID (id open weather map)
- par localisation (latitude, longitude)

L'application propose aussi un helper à lancer avec la commande :
```
$> h 
```

## Version des librairies utilisé

- gradle : 6.7 
- jdk : 15.0.2
- javaFx : 11.0.2
- java-json :

## Polices utilisées
- Open-Sans:  distribuée par Google Fonts.

## Icones
- Icones réalisées par Freepik et Vectors Market (flaticon.com)

<br><br>
Made by :
- Tom Ami
- John Kondozopulos
- Thomas Nanecou
- Paul Alligier 
 
