
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This machine reads the tape from left to right and replaces any 1’s with 0’s and any 0’s with 1’s.
 * It stops, by entering the halt state, when it encounters a B in the input. 
 * @author zhanjing
 */
public class Turing {

    // this is the main function to test the turing machine
    public static void main(String args[]) {
        Turing machine = new Turing(1);
        Transition one = new Transition('0', Transition.RIGHT, 0);
        Transition two = new Transition('1', Transition.RIGHT, 0);
        Transition three = new Transition('B', Transition.LEFT, 1);
        machine.addTransition(0, '0', two);
        machine.addTransition(0, '1', one);
        machine.addTransition(0, 'B', three);
        String inTape = "11111100010101";
        System.out.println(inTape);
        String outTape = machine.execute(inTape);
        System.out.println(outTape);
    }
    
    // define an array of size 40 
    char[] output = new char[40];
    int currentState;
    static int FINALSTATE = 1;
    char currentTape;
    int head;
    Transition[][] transitTable;

    public Turing(int n) {
        transitTable = new Transition[n+1][3];
        //set the initial read/write head to 20
        head = 20;
        currentState = 0;
    }

    // this method assigns a transition to a specific state when encounter a tape
    public void addTransition(int state, char tape, Transition t) {
        if (tape == 'B') {
            transitTable[state][2] = t;
        } else {
            transitTable[state][Integer.parseInt(tape + "")] = t;
        }
    }

    // this method executes the capability of this turing machine
    // it reads the tape from left to right and replaces any 1’s with 0’s and any 0’s with 1’s
    //It stops, by entering the halt state, when it encounters a B in the input. 
    public String execute(String inTape) {
        int i = 0;
        while (currentState != FINALSTATE) {
            int index;
            if (i >= inTape.length() || inTape.charAt(i) == 'B') {
                index = 2;
            } else {
                index = Integer.parseInt("" + inTape.charAt(i));
            }
            Transition tempT = transitTable[currentState][index];
            output[head] = tempT.tape;
            head += tempT.direction;
            currentState = tempT.state;
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < output.length; i ++) {
            if (output[i] == '\0') {
                sb.append("B");
            } else {
                sb.append(output[i]);
            }
        }
        return sb.toString();
    }

    // this is the transition class which simulates the transition of the turing machine
    static class Transition {

        static int RIGHT = 1;
        static int LEFT = -1;
        int state;
        int direction;
        char tape;

        Transition(char tape, int direction, int state) {
            this.tape = tape;
            this.direction = direction;
            this.state = state;
        }
    }
}
