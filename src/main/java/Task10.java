import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Task10 {

    public static void main(String[] args) throws Exception {

        //Task4
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

//           // Task5
//        terminal.setCursorPosition(6, 8);
//        terminal.putCharacter('A');
//        terminal.setCursorPosition(20, 5);
//        terminal.putCharacter('B');
//
//
//        terminal.setCursorPosition(6, 8);
//        for (int column = 1; column < 10; column++) {
//            terminal.setCursorPosition(6,+column);
//            terminal.putCharacter('X');
//        }

//        }

//            //Task6
//        String s = "This is a String printed out in Lanterna by iterating over the characters";
//             for(int i=0; s.length(); i++){
//            terminal.setCursorPosition(i,14);
//            terminal.putCharacter(s.charAt(i));
//}}}



        // task 7 & 8 &9

        boolean continueReadingInput=true;
        int x = 10;
        int y = 10;
        final char player = '0';
        terminal.setCursorPosition(x,y);
        terminal.putCharacter(player);


        while (continueReadingInput) {

            KeyStroke keyStroke = null;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();   // terminalen vi jobbar på, kollar om det har hänt något.
                // om det har hänt något så blir keystroke det tangentet som tycktes och då avslutas loopen.
            } while (keyStroke == null);


            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter(); // used Character, not char because it might be null
            System.out.println("keyStroke.getKeyType(): " + type
                    + " keyStroke.getCharacter(): " + c);

            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
                terminal.close();
                System.out.println("quit");

            }
            //task9
            int oldX=x;
            int oldY=y;
            switch (keyStroke.getKeyType()) {
                case ArrowDown:
                    y += 1;
                    break;
                case ArrowUp:
                    y -= 1;
                    break;
                case ArrowRight:
                    x += 1;
                    break;
                case ArrowLeft:
                    x -= 1;
                    break;

            }
            //task10
            terminal.setCursorPosition(x,y);
            terminal.putCharacter(player);
            terminal.setCursorPosition(oldX,oldY);  //varför sätter man denna ovan?
            terminal.putCharacter(' ');
            terminal.flush();
        }

    }}
