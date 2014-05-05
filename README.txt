The approach for the solution consists in a backtracking.
Considering all the zombies in the graveyard, the algorithm iterates for each of them and add them to a partial solution, taking 
into account the appearance and recharged time. 

In case there are zombies, it should consider them (if visible), adding the transition time to the zombie's coordinate and the 
recharged time needed to be able to smash the zombie.
When there are no more zombies to evaluate, it should verify if the partial solution is the optimal till the moment and if so, 
it keeps the amount of zombies.


#INPUT FILE TEST CASES

(extra test cases)

Case #4: 

3
1 0 0
-1 0 0
10 10 850

You can smash the zombie at (10,10) because it is visible until 1850 ms.

Case #5: 

3
1 0 0
-1 0 0
10 10 800

You can NOT smash the zombie at (10,10) because it is visible until 1800 ms and it takes 1850 ms to reach that coordinate.

Case #6: 

4
5 5 0
-5 5 500
5 -5 1500
-5 -5 0

You can NOT smash the zombie at (-5,-5) for the optimal solution because it is visible until 1000 ms and it would take 3500 ms 
to reach that coordinate.

Case #7: 

4
5 5 0
-5 5 500
5 -5 1500
7 -7 2250

You can smash the zombie at (7,-7) because it is visible until 3250 ms. It took 2500 ms to smash (5,5), (-5,5) and (5,-5) and 
then you have to wait 750 ms for the smasher to be recharged until 3250 ms.
