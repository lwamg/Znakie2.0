import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class GameOver {

    public boolean gameOver(Position p, Position old, boolean continueReadingInput){

        if(p.x == old.x && p.y == old.y){
            continueReadingInput = false;
                    String g = "GAME OVER \t WINNER \u0394 \t LOSER X";
                    for (int i = 0; i < g.length(); i++) {
                        terminal.setCursorPosition(30+i, 10);
                        terminal.putCharacter(g.charAt(i));
            return continueReadingInput;

        }

    }

}
