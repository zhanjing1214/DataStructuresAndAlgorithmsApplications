### App6

Implement a Turing machine that we simulate M = (Q,S,G,¶,q0,B): where 

Q, a finiteset of states. For this program Q = {0,1,2,…n-1} and is selected bythe client programmer. 

G = { 0,1,B } is thefinite set of allowable tape symbols 

B, a symbol of G, is the blank 

S = { 0,1}, a subset of G not including B, is the set of input symbols 

¶: Q x G  ® Q x G x {L, R} (¶ may, however, be undefined for some arguments) 

q0= 0 is the initial state  

Suppose that we wanted ourprogram to simulate the machine with the following values for delta:

 ¶(q0,0) =(q0,1,R)    

 ¶(q0,1) =(q0,0,R)  

 ¶(q0,B) =(q1,B,L)  

This machine reads the tapefrom left to right and replaces any 1’s with 0’s and any 0’s with 1’s. It stops, by entering the halt state, when it encounters a B in the input. It is called a one state machine (there is always an additional Halt state).