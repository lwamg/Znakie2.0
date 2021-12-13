import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Movement {
    int speed = 35;
    int index = 0;
    Keystroke keyStroke = null;

        do {
            index++;
            if (index %speed == 0) {
                if (latestType != null) {

                    switch (latestType) {
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

                    if (positionX.size() > 3){
                        for (int i = 0 ; i < positionX.size(); i++) {
                            if (x == positionX.get(i) && y == positionY.get(i)) {
                                continueReadingInput = false;
                                GameOver.gameOver(terminal, score);
                            }
                        }

                    }
                    positionX.add(x);
                    positionY.add(y);
                    for(int i = 0; i < positionX.size(); i++){
                        terminal.setCursorPosition(positionX.get(i), positionY.get(i));
                        if (i == positionX.size() - 1){
                            terminal.putCharacter(p.head);
                        }
                        else {
                            terminal.putCharacter(p.player);
                        }
                    }

                    if (x == fruit.x && y == fruit.y) {
                        positionX.add(x);
                        positionY.add(y);
                        positionX.add(x);
                        positionY.add(y);
                        randomNumber = ThreadLocalRandom.current().nextInt(4, 40);
                        randomNumber2 = ThreadLocalRandom.current().nextInt(4, 40);
                        terminal.setCursorPosition(randomNumber, randomNumber2);
                        terminal.putCharacter(fruit.fruit);
                        fruit.x = randomNumber;
                        fruit.y = randomNumber2;
                        score++;
                        if (speed > 8) {
                            speed = speed -3;
                        }

                        for(int i = 0; i < positionX.size(); i++){
                            terminal.setCursorPosition(positionX.get(i), positionY.get(i));
                            terminal.putCharacter(p.player);
                        }
                    }
                    terminal.setCursorPosition(positionX.get(0), positionY.get(0));
                    terminal.putCharacter(' ');
                    positionX.remove(0);
                    positionY.remove(0);
                    terminal.flush();
                }
            }

            boolean stopReading = frameWork(terminal, block,x,y,score,continueReadingInput);
            continueReadingInput = stopReading;


            Thread.sleep(5);
            keyStroke = terminal.pollInput();

        } while (keyStroke == null);

        KeyType type = keyStroke.getKeyType();
        Character c = keyStroke.getCharacter();
        latestType = type;

        if (c == Character.valueOf('q')) {
            continueReadingInput = false;
            terminal.close();
            System.out.println("quit");
        }
        terminal.flush();
}

