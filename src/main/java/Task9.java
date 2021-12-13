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


            TerminalSize ts = new TerminalSize(100, 100);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(ts);
            Terminal terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);

            boolean continueReadingInput=true;

            Welcome welcome = new Welcome();
            welcome.welcomeText(terminal);

            final char block = '\u2588';
            KeyType latestType = null;

            KeyStroke keyStroke = null;

            Position[] frameDown = new Position[100];
            for(int i = 0;i<100;i++){
                frameDown[i] = new Position(i, 100);
            }

            for (Position pRam : frameDown) {
                terminal.setCursorPosition(pRam.x, pRam.y);
                terminal.putCharacter(block);
            }

            Position[] frameUp = new Position[100];
            for(int i = 0;i<100;i++){
                frameUp[i] = new Position(i, 0);
            }

            for (Position pRam : frameUp) {
                terminal.setCursorPosition(pRam.x, pRam.y);
                terminal.putCharacter(block);
            }

            Position[] frameRight = new Position[100];
            for(int i = 0;i<100;i++){
                frameRight[i] = new Position(100, i);
            }

            for (Position pRam : frameRight) {
                terminal.setCursorPosition(pRam.x, pRam.y);
                terminal.putCharacter(block);
            }

            Position[] frameLeft = new Position[100];
            for(int i = 0;i<100;i++){
                frameLeft[i] = new Position(0, i);
            }

            for (Position pRam : frameLeft) {
                terminal.setCursorPosition(pRam.x, pRam.y);
                terminal.putCharacter(block);
            }

            Position p = new Position(15,10);
//            Position pOld = new Position(p.x, p.y);
//            Position pOldOld = new Position(pOld.x, pOld.y);
            ArrayList<Integer> positionX = new ArrayList();
            ArrayList<Integer> positionY = new ArrayList();
    //        positions.add(p);
            positionX.add(15);
            positionY.add(10);
            positionX.add(15);
            positionY.add(10);
            int x = 15;
            int y = 10;

            int randomNumber = ThreadLocalRandom.current().nextInt(1,40);
            int randomNumber2 = ThreadLocalRandom.current().nextInt(1,40);
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
//                            pOldOld.x = pOld.x;
//                            pOldOld.y = pOld.y;
//                            pOld.x = p.x;
//                            pOld.y = p.y;

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
                                randomNumber = ThreadLocalRandom.current().nextInt(1, 70);
                                randomNumber2 = ThreadLocalRandom.current().nextInt(1, 70);
                                terminal.setCursorPosition(randomNumber, randomNumber2);
                                terminal.putCharacter(fruit.fruit);
                                fruit.x = randomNumber;
                                fruit.y = randomNumber2;
                                if (speed > 10) {
                                    speed = speed - 5;
                                }
                            }
                            terminal.setCursorPosition(positionX.get(0), positionY.get(0));
                            terminal.putCharacter(' ');
                            positionX.remove(0);
                            positionY.remove(0);
                            terminal.flush();


                        }

                        }



                    boolean crashIntoFrameOrItself = false;

                    for (Position pRam : frameRight) {
                        if (x == pRam.x && y == pRam.y) {
                            crashIntoFrameOrItself = true;
                            break;
                        }
                    }
                    for (Position pRam : frameLeft) {
                        if (x == pRam.x && y == pRam.y) {
                            crashIntoFrameOrItself = true;
                        }
                    }
                    for (Position pRam : frameDown) {
                        if (x == pRam.x && y == pRam.y) {
                            crashIntoFrameOrItself = true;
                        }
                    }
                    for (Position pRam : frameUp) {
                        if (x == pRam.x && y == pRam.y) {
                            crashIntoFrameOrItself = true;
                        }
                    }


                      //   if ( p.x == pOld.x && p.y==pOld.y) {
                        //    crashIntoFrameOrItself = true;
                       // }

                    if (crashIntoFrameOrItself) {
                        GameOver.gameOver(terminal);
                        System.out.println("Game over");
                    }





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

//                if (x == xMonster && y == yMonster) {
//                    continueReadingInput = false;
//                    System.out.println("GAME OVER");
//                    String g = "GAME OVER \t WINNER \u0394 \t LOSER X";
//                    for (int i = 0; i < g.length(); i++) {
//                        terminal.setCursorPosition(30+i, 10);
//                        terminal.putCharacter(g.charAt(i));
//
//
//                    }
//                }


//                int oldXMonster= xMonster;
//                int oldYMonster= yMonster;


/*
                switch (keyStroke.getKeyType()) {
                    case ArrowDown:
                        y += 1;
//                        if(yMonster<=y){
//                        yMonster +=1;}
//                        else  if (yMonster>=y){
//                            yMonster-=2;
//                        }
                        break;
                    case ArrowUp:
                        y -= 1;
//                        if(yMonster<=y){
//                            yMonster +=1;}
//                        else  if (yMonster>=y) {
//                            yMonster -= 2;
//                        }
                        break;
                    case ArrowRight:
                        x += 1;
//                        if(xMonster <= x) {
//                            xMonster += 2;
//                        }
//                        else if(xMonster >= x){
//                            xMonster -=2;
//                        }
                        break;
                    case ArrowLeft:
                        x -= 1;
//                        if(xMonster <= x) {
//                            xMonster += 1;
//                        }
//                        else if(xMonster >= x){
//                            xMonster -=2;
//                        }
                        break;

                }

 /*               if (x == xMonster && y == yMonster) {
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);

                }
                else
                terminal.setCursorPosition(oldX,oldY);
                terminal.putCharacter(player);
                terminal.setCursorPosition(x,y);
                terminal.putCharacter(player);*/

//                terminal.setCursorPosition(oldXMonster,oldYMonster);
//                terminal.putCharacter(' ');
//                terminal.setCursorPosition(xMonster,+yMonster);
//                terminal.putCharacter(monster);


                terminal.flush();


            }
        }
}

