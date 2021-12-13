import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class GameOver {

    public static void gameOver(Terminal terminal, int score)throws Exception {
        String g = "GAME OVER! You scored " + score;
        terminal.clearScreen();
        for (int i = 0; i < g.length(); i++) {
            terminal.setCursorPosition(30 + i, 10);
            terminal.putCharacter(g.charAt(i));

        }

    }
}

