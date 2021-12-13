import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Task9 {

        public static void main(String[] args) throws Exception {


            TerminalSize ts = new TerminalSize(100, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(ts);
            Terminal terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);

            boolean continueReadingInput=true;

            Welcome welcome = new Welcome();
            welcome.welcomeText(terminal);

            final char block = '\u2588';
            KeyType latestType = null;
            int score=0;

            KeyStroke keyStroke = null;

            Position p = new Position(15,10);
            ArrayList<Integer> positionX = new ArrayList();
            ArrayList<Integer> positionY = new ArrayList();
            positionX.add(15);
            positionY.add(10);
            positionX.add(15);
            positionY.add(10);
            int x = 15;
            int y = 10;

            frameWork(terminal, block, p.x, p.y,score,continueReadingInput);

            int randomNumber = ThreadLocalRandom.current().nextInt(3,40);
            int randomNumber2 = ThreadLocalRandom.current().nextInt(3,40);
            Fruit fruit = new Fruit(randomNumber,randomNumber2);
            terminal.setCursorPosition(fruit.x,fruit.y);
            terminal.putCharacter(fruit.fruit);

            int speed = 40;
            while (continueReadingInput) {
                int index = 0;

               keyStroke = null;

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
                            positionX.add(x);
                            positionY.add(y);
                            for(int i = 0; i < positionX.size(); i++){
                                terminal.setCursorPosition(positionX.get(i), positionY.get(i));
                                terminal.putCharacter(p.player);
                            }

                            if (x == fruit.x && y == fruit.y) {
                                positionX.add(x);
                                positionY.add(y);
                                randomNumber = ThreadLocalRandom.current().nextInt(4, 40);
                                randomNumber2 = ThreadLocalRandom.current().nextInt(4, 40);
                                terminal.setCursorPosition(randomNumber, randomNumber2);
                                terminal.putCharacter(fruit.fruit);
                                fruit.x = randomNumber;
                                fruit.y = randomNumber2;
                                score++;
                                if (speed > 10) {
                                    speed = speed - 5;
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

                    boolean stopReadingInput=frameWork(terminal, block,x,y,score,continueReadingInput);
               continueReadingInput=stopReadingInput;
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
        }

        public static boolean frameWork(Terminal terminal, char block, int x, int y, int score, boolean continueReadingInput)throws Exception{
            boolean crashIntoFrameOrItself = false;


        Position[] frameDown = new Position[100];
        for(int i = 0;i<100;i++){
            frameDown[i] = new Position(i, 45);
        }

        for (Position pRam : frameDown) {
            terminal.setCursorPosition(pRam.x, pRam.y);
            terminal.putCharacter(block);
            if (x == pRam.x && y == pRam.y) {
                crashIntoFrameOrItself = true;
            }
        }

        Position[] frameUp = new Position[100];
        for(int i = 0;i<100;i++){
            frameUp[i] = new Position(i, 0);
        }

        for (Position pRam : frameUp) {
            terminal.setCursorPosition(pRam.x, pRam.y);
            terminal.putCharacter(block);
            if (x == pRam.x && y == pRam.y) {
                crashIntoFrameOrItself = true;
            }
        }

        Position[] frameRight = new Position[100];
        for(int i = 0;i<100;i++){
            frameRight[i] = new Position(100, i);
        }

        for (Position pRam : frameRight) {
            terminal.setCursorPosition(pRam.x, pRam.y);
            terminal.putCharacter(block);
            if (x == pRam.x && y == pRam.y) {
                crashIntoFrameOrItself = true;
            }
        }

        Position[] frameLeft = new Position[100];
        for(int i = 0;i<100;i++){
            frameLeft[i] = new Position(0, i);
        }

        for (Position pRam : frameLeft) {
            terminal.setCursorPosition(pRam.x, pRam.y);
            terminal.putCharacter(block);
            if (x == pRam.x && y == pRam.y) {
                crashIntoFrameOrItself = true;
            }

            if (crashIntoFrameOrItself) {
                GameOver.gameOver(terminal,score);
                continueReadingInput=false;

            }
        }return continueReadingInput;
    }
}

