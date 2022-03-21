:- dynamic(glitter/1).
:- dynamic(breeze/1).
:- dynamic(stench/1).
:- dynamic(room/2).
:- dynamic(visited/1).
:- dynamic(hunter/1).
:- dynamic(hasGold/1).

room(0,0).
room(0,1).
room(1,0).

visited(room(0,0)).


hunter(room(0,0)).






adjacent(X1,Y1,X2,Y2) :-
	room(X1,Y1),
    room(X2,Y2),
    X1 is X2,
    Y1 is Y2 + 1.


adjacent(X1,Y1,X2,Y2) :-
	room(X1,Y1),
    room(X2,Y2),
    X1 is X2,
    Y1 is Y2 - 1.


adjacent(X1,Y1,X2,Y2) :-
	room(X1,Y1),
    room(X2,Y2),
    X1 is X2 + 1,
    Y1 is Y2.


adjacent(X1,Y1,X2,Y2) :-
	room(X1,Y1),
	room(X2,Y2),
    X1 is X2 - 1,
    Y1 is Y2.


adjacentrooms(room(X,Y),Rooms) :-
    findall(room(X2,Y2),adjacent(X,Y,X2,Y2),Rooms).

notAPotentialPit(room(X,Y)):-
	visited(room(X,Y));
	room(X2,Y2),
	adjacent(X,Y,X2,Y2),
	visited(room(X2,Y2)),
	not(breeze(room(X2,Y2))).
	
potentialPit(X,Y):-
	room(X,Y),
	not(notAPotentialPit(room(X,Y))).



notACertainPit(X,Y,X2,Y2):-
	room(X3,Y3),
	adjacent(X2,Y2,X3,Y3),
	X3 \= X,
	Y3 \= Y,
	potentialPit(X3,Y3).
	
certainPit(X,Y,X2,Y2):-
	not(notACertainPit(X,Y,X2,Y2)).

pit(X,Y):-
	room(X,Y),
	potentialPit(X,Y),
	room(X2,Y2),
	adjacent(X,Y,X2,Y2),
	breeze(room(X2,Y2)),
	certainPit(X,Y,X2,Y2).




notAPotentialWumpus(room(X,Y)):-
	visited(room(X,Y));
	room(X2,Y2),
	adjacent(X,Y,X2,Y2),
	visited(room(X2,Y2)),
	not(stench(room(X2,Y2))).
	
potentialWumpus(X,Y):-
	room(X,Y),
	not(notAPotentialWumpus(room(X,Y))).



notACertainWumpus(X,Y,X2,Y2):-
	room(X3,Y3),
	adjacent(X2,Y2,X3,Y3),
	X3 \= X,
	Y3 \= Y,
	potentialWumpus(X3,Y3).
	
certainWumpus(X,Y,X2,Y2):-
	not(notACertainWumpus(X,Y,X2,Y2)).

wumpus(X,Y):-
	room(X,Y),
	potentialWumpus(X,Y),
	room(X2,Y2),
	adjacent(X,Y,X2,Y2),
	stench(room(X2,Y2)),
	certainWumpus(X,Y,X2,Y2),!.


gold(room(X,Y)):-
	glitter(room(X,Y)).
	
safe(room(X,Y)):-
	not(potentialPit(X,Y)),
	not(potentialWumpus(X,Y)).
	
allsafe(Safe) :-
    findall(room(X,Y),(room(X,Y),safe(room(X,Y))),Safe).
	
grabGold(room(X,Y)):-
	hunter(room(X,Y)),
	gold(room(X,Y)).
	
shootWumpus(room(X,Y),room(Xnew,Ynew),Path):-
	room(X,Y),
	wumpus(X,Y),
	room(X2,Y2),
	hunter(room(X2,Y2)),
	room(Xnew,Ynew),
	adjacent(X,Y,Xnew,Ynew),
	stench(room(Xnew,Ynew)),
	moveFromTo(room(X2,Y2),room(Xnew,Ynew),Path),!.




connected(room(X,Y),room(Xt,Yt)) :- 
	adjacent(X,Y,Xt,Yt).


path(room(X,Y),room(Xt,Yt),Path) :-
       travel(room(X,Y),room(Xt,Yt),[room(X,Y)],Q), 
       reverse(Q,Path).
	   
travel(room(X,Y),room(X,Y),_,[room(X,Y)]).

travel(room(X,Y),room(Xt,Yt),P,[room(Xt,Yt)|P]) :- 
       connected(room(X,Y),room(Xt,Yt)).
	   
	   
travel(room(X,Y),room(Xt,Yt),Visited,Path) :-
       connected(room(X,Y),room(Xnew,Ynew)),
		visited(room(Xnew,Ynew)),
       room(Xnew,Ynew) \== room(Xt,Yt),
       \+member(room(Xnew,Ynew),Visited),
       travel(room(Xnew,Ynew),room(Xt,Yt),[room(Xnew,Ynew)|Visited],Path).
	   
moveFromTo(room(X,Y),room(Xt,Yt),Path):-
	hunter(room(X,Y)),
	path(room(X,Y),room(Xt,Yt),Path),!.
	
bestAction(Action):-
	wumpus(X2,Y2),
	shootWumpus(room(X2,Y2),room(Xhunter,Yhunter),Path),
	Action = [1,room(Xhunter,Yhunter),Path],
	!.
	
bestAction(Action):-
	hunter(room(X,Y)),
	grabGold(room(X,Y)),
	Action = [2,room(X,Y),[room(X,Y)]],
	!.
	
bestAction(Action):-
	hunter(room(X,Y)),
	room(Xt,Yt),
	not(visited(room(Xt,Yt))),
	moveFromTo(room(X,Y),room(Xt,Yt),Path),
	safe(room(Xt,Yt)),
	not(visited(room(Xt,Yt))),
	Action = [3,room(Xt,Yt),Path],
	!.
	
	bestAction(Action):-
	hunter(room(X,Y)),
	room(Xt,Yt),
	not(visited(room(Xt,Yt))),
	moveFromTo(room(X,Y),room(Xt,Yt),Path),
	not(visited(room(Xt,Yt))),
	Action = [4,room(Xt,Yt),Path],
	!.
	