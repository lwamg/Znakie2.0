
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

public class Welcome {
    String g = "WELCOME TO ZNAKIE GAME \n PRESS S TO START THE GAME";


public void welcomeText(Terminal terminal)throws Exception{


    KeyStroke keyStroke = null;

    for (int i = 0; i < g.length(); i++) {
        terminal.setCursorPosition(26 + i, 23);
        terminal.putCharacter(g.charAt(i));
    }
    while (keyStroke== null){
        Thread.sleep(5); // might throw InterruptedException
        keyStroke = terminal.pollInput();
    }

    Character s = keyStroke.getCharacter();

    if (s == Character.valueOf('s')) {
        System.out.println("start");
        terminal.clearScreen();
    }

}}
