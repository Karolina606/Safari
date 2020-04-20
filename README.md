# Safari
Simple simulation of safari live.

### Description 
Safari owns its map which has width and height. On the safari can live Lions, Zebras and Elephants. All of those move and eat.
* Lion eats another animals
* Zebra eats Grass which is growing on Safari
* Elephant eats leaves from trees 
* Sometimes on the safari human can appear he hunts lions.

Durign the simulation organisms have to interact with each others, they are eating, dying, losing they energy and increasing species abundance.

Running the simulation (example)
---
```console
foo@bar:~/.../Safari$ ./gradlew run --args="-width 10 -height 10 -maxIter 20 -lion 1 -zebra 2 -elephant 3"
```
It will start simulation:
* with map 10x10 
* makes 10 iterations
* on safari on the begining appear 1 lion 2 zebras and 3 elephants 

None of the parameters are necessary, all have its default values:
* width = 4
* height = 4
* maxIter = 10
* lion = 1
* zebra = 1
* elephant = 1

It is only up to the user which parameters to provide.

In every iteration informations about animals are saved to "Safari/raport.csv"

### Simulation process
1. Make a map
2. Place given numbers of animals on safari (positions are random)
3. Amount of trees and grasses are random (like a position)
4. In every iteration Human can appear (with some possibility) and hunt a lion
4. In every iteration all animals make some actions (move, eat, reproduct, attack), population is changing
5. In every iteration animals data are saved
6. Simulation ends, write data to file

Diagrams
---
#### First class diagram

|-![alt text](https://github.com/Karolina606/Safari/blob/master/doc/diagrams/png/classDiagram.png "class diagram")-|

#### Activity diagram

|-![alt text](https://github.com/Karolina606/Safari/blob/master/doc/diagrams/png/activityDiagram.png "class diagram")-|

#### Sequence diagram

|-![alt text](https://github.com/Karolina606/Safari/blob/master/doc/diagrams/png/sequenceDiagram.png "class diagram")-|

