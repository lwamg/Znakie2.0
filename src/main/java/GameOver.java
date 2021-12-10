import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class GameOver {

    public static void gameOver(Terminal terminal)throws Exception {
        String g = "GAME OVER \t WINNER \u0394 \t LOSER X";
        for (int i = 0; i < g.length(); i++) {
            terminal.setCursorPosition(30 + i, 10);
            terminal.putCharacter(g.charAt(i));

        }

    }
}

