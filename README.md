# Proyecto-Tennis-CC5212
This repository contains all the information about the final project of the course CC5212. 

##Traducir luego

El proyecto esta basado en la base de datos extraida de la siguiente pagina: https://www.kaggle.com/datasets/ehallmar/a-large-tennis-dataset-for-atp-and-itf-betting?select=all_tournaments.csv

Se uso solo la trabla all_matches, y se le realizo una limpieza para tener los datos de interes.


Nuestro objetivo es obtener cuales son los jugadores o duplas mas ganadoras en la historia, y luego ver cuales son los que mas les ganaron a esas duplas y quienes fueron los que mas perdieron contra esas duplas.

Primero hicimos un mapper que obtiene todas las tuplas de la forma (Ganador,[perdedores]), y con el reduce iteramos sobre la listade perdedores haciendo un output de (ganador,1) por cada elemento. De esta forma, usando un codigo practcamente igual al del Lab 5, pudimos obtener un conteo de veces que cada jugador habia ganado. Y luego con el Sort pudimos obtener un top. 

De este top vimos 2 datos interesantes. El primero es que el Top1 es un jugador que ninguno de los del grupo conocia, ya que es de otra generacion. Por lo tanto decidimos que hariamos una consulta a ver quienes le habian ganado a el, de forma de ilustrarnos un poco de quien podria haber sido su maximo rival (algo asi como federer-Nadal). O si por el contrario nadie habia podido hacerle competencia, lo cual tambien podria ser un caso.

El otro dato interesante fue que el 3er lugar lo ocupa un duo, que al investigarlos nos percatamos que son una pareja de gemelos identicos y que son considerados como la pareja de tennis mas exitosa en la historia de ese deporte. Por lo que nuestra otra consulta busca saber a quienes les han ganado, y cuantas veces. 

Con esto podremos tener una vision un poco mas general de que jugadores son los mas exitosos, y a la vez quienes son aquellos que mejor rinden contra ellos.

##Limpieza

Es importante destacar que descartaremos muchas columnas. Y esto sera porque no las necesitaremos para el proceso. Para esto se uso un jupyter notebook, el cual incluire en este repositorio. Decidimos usar un notebook ya que es mas simple de usar, estamos mas familiarizados, y a pesar de que estamos trabajando con un dataset bastante grande, las operaciones de remover columnas son lo suficientemente simples para no necesitar algun otro metodo o mayor poder computacional.



Desarrollo:












