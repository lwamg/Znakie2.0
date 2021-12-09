import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class test {
    public static void main(String[] args) throws Exception {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);


//        terminal.setCursorPosition(6, 8);
//        terminal.putCharacter('A');
//        terminal.setCursorPosition(20, 5);
//        terminal.putCharacter('B');
//
//
//        for (int column = 1; column < 10; column++) {
//            terminal.setCursorPosition(0,4 +column);
//            terminal.putCharacter('X');
//        }

//        String message = "This is a String printed out in Lanterna by iterating over the characters";
//             for(int i=0; message.length(); i++){
//            terminal.setCursorPosition(i,14);
//            terminal.putCharacter(message.charAt(i));
//
//                }


//        terminal.flush();

        //task 7


        boolean continueReadingInput=true;

        while (continueReadingInput) {

            KeyStroke keyStroke = null;
            do {
                Thread.sleep(2); // might throw InterruptedException
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

        }}}

