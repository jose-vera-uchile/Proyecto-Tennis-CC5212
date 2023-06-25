# Proyecto-Tennis-CC5212
This repository contains all the information about the final project of the course CC5212. 

##Traducir luego

El proyecto esta basado en la base de datos extraida de la siguiente pagina: https://www.kaggle.com/datasets/ehallmar/a-large-tennis-dataset-for-atp-and-itf-betting?select=all_tournaments.csv

Se usaron las siguientes tablas y sus atributos:

All_matches:

Start_date y end_date: las fechas de inicio y final en formato YYYY-MM-DD

Location: El nombre del pais anfitrion

court_surface: El tipo de superficie, podria ser clay, hard, grass, etc.

prize_money y currency: El dinero ganado por ganar dicho partido, seguido de la divisa del premio.

year: el año

Player_id y Player name : El id unico del jugador, seguido del nombre del jugador. Doubles matches are also available (player_id field will have both names sorted and joined by an underscore ('_'). For example, a doubles team of Roger Federer and Novak Djokovic would appear as novak-djokovic_roger-feder in the player_id column of the all_matches.csv file.

tournament: Id del torneo

Round: la ronda del partido, por ejemplo Semifinals, Finals, etc.

[Falta terminar de ver que columnas se usaran]




Queremos ilustrarnos un poco, con respecto a cuales son los jugadores que mas han ganado en la historia, y tambien contra quien han perdido mas veces. Para esto haremos lo siguiente:

1- Top 3 de jugadores que mas victorias como individuales tienen en la historia, seguido de su porcentaje. Se veran de la forma: 

Roger-Federer##230##55.4 (es un ejemplo)

2- Top 3 de duplas de jugadores con mas victorias en Dobles, seguida de su porcentaje. Se veria algo asi:
  Rafael-Nadal_Roger-Federer##15##70.3

3- Top 3 de jugadores con mas victorias en duplas, seguidas de su porcentaje (jugadores, no duplas). Se veria igual que en el primero, algo asi:

Roger-Federer##8##72.2

4- Top 3 de jugadores que mas victorias tiene en general, seguidos de su porcentaje, y el desglose por categoria (ind. y luego dobles)

Roger-Federer##238##60.2##230##55.4##8##72.2

5- Finalmente, Top 3 de jugadores con mas victorias contra cada uno de los miembros del top 3. la respuesta se vera algo asi:

vsTop1##Rafael-Nadal##25
vsTop1##Novak-Djokovic##23
vsTop1##Andy-Murray##12
vsTop2##Novak-Djokovic##15
[...]

Con esto podremos tener una vision un poco mas general de que jugadores son los mas exitosos, y a la vez quienes son aquellos que mejor rinden contra ellos.



Es importante destacar que descartaremos muchas columnas. Y esto sera porque no las necesitaremos para el proceso. Para esto se uso un jupyter notebook, el cual incluire en este repositorio. Decidimos usar un notebook ya que es mas simple de usar, estamos mas familiarizados, y a pesar de que estamos trabajando con un dataset bastante grande, las operaciones de remover columnas son lo suficientemente simples para no necesitar algun otro metodo o mayor poder computacional.
