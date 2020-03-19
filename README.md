# Mouhamed NDOYE M2GL

## Microservice project

#### Vue d'implémentation de la technologie Spring boot
![spring_boot](https://user-images.githubusercontent.com/49942742/77066557-4a170b80-69db-11ea-99c6-9e32730a6ad0.PNG)

#### Vue d'implémentation de microservice
![](capture_ecran/msa.png)


#### Etude comparative entre l'architecture SOA et MSA
-   **Architecture SOA :** c'est un ensemble de services. Ces services communiquent entre eux.
La communication peut impliquer soit un simple transfert de données, soit deux ou plusieurs services
coordonnant une activité.

-   **Architecture Microservices :** c'est un style qui structure une application sous la forme d'une
collection de petits services autonomes modélisés autour d'un domaine précis.

-   **Différence clé entre SOA et Microservices :** Lorsqu'on compare Microservices et SOA, les deux
s'appuient sur des services en tant que composant principal, mais leurs caractéristiques varient
considérablement.

    Les services SOA sont gérés dans l'organisation par un registre qui agit comme une liste de services
    . Les applications doivent rechercher les services dans le registre et appeler le service. SOA est
    comme un orchestre où chaque artiste joue avec son instrument tandis que le directeur musical donne
    des instructions à tous.
    
    D'ailleurs, Microservices est une forme de SOA dans lequel les applications sont construites sous la 
    forme d'une collection de différents services plus petits au lieu d'une application entière. Microservices
    est comme une troupe dans laquelle chaque danseur est indépendant et sait ce qu'il doit faire. Ainsi,
    s'ils manquent quelques étapes, ils savent comment revenir à la bonne séquence.
    
    -   **Comparaison détaillée entre SOA et Microservices :**
    
    |                     | SOA                                                                                                           | Microservices                                                               |
    | --------------------|---------------------------------------------------------------------------------------------------------------| ----------------------------------------------------------------------------|
    | Conception          | En SOA, les composants logiciels sont exposés au monde extérieur pour une utilisation sous forme de services. | Microservices fait partie de SOA. C’est une implémentation de SOA.          |
    | Dépendance          | Les unités métiers sont dépendantes.                                                                          | Ils sont indépendants les uns des autres.                                   |
    | Taille du logiciel  | La taille du logiciel est supérieure à celle de tout logiciel conventionnel                                   | La taille du logiciel est toujours petite dans l’architecture Microservices |
    | Pile de technologie | La pile des technologies est inférieure à celle de Microservices.                                             | La pile des technologies de Microservices pourrait être très volumineuse    |
    | Tâches effectuées   | Les applications SOA sont conçues pour effectuer plusieurs tâches de l’entreprise.                            | Ils sont conçus pour effectuer une tâche unique.                            |
    | Rentabilité         | Plus rentable.                                                                                                | Moins rentable                                                              |
    | Évolutivité         | SOA est moins évolutif comparée aux Microservices.                                                            | Très évolutif.                                                              |


#### Diagramme de déploiement de la solution

![GitHub Logo](capture_ecran/deployment_diagram.png)
