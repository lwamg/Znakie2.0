import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
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


            TerminalSize ts = new TerminalSize(100, 120);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(ts);
            Terminal terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);
            terminal.setForegroundColor(TextColor.ANSI.GREEN);

            boolean continueReadingInput=true;

            Welcome welcome = new Welcome();
            welcome.welcomeText(terminal);


            final char monster = '\u0394';
            KeyType latestType = null;



            KeyStroke keyStroke = null;
            Position p = new Position(15,10);
            Position pOld = new Position(p.x, p.y);
            Position pOldOld = new Position(pOld.x, pOld.y);

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
                    if (index %speed == 0){
                        if(latestType != null) {
                            pOldOld.x = pOld.x;
                            pOldOld.y = pOld.y;
                            pOld.x = p.x;
                            pOld.y = p.y;


                            switch (latestType) {
                                case ArrowDown:
                                    p.y += 1;
                                    break;
                                case ArrowUp:
                                    p.y -= 1;
                                    break;
                                case ArrowRight:
                                    p.x += 1;
                                    break;
                                case ArrowLeft:
                                    p.x -= 1;
                                    break;
                            }
                            if (p.x== fruit.x && p.y == fruit.y){
                                randomNumber = ThreadLocalRandom.current().nextInt(1,40);
                                randomNumber2 = ThreadLocalRandom.current().nextInt(1,40);
                                terminal.setCursorPosition(randomNumber, randomNumber2);
                                terminal.putCharacter(fruit.fruit);
                                fruit.x = randomNumber;
                                fruit.y = randomNumber2;
                                if (speed >10){
                                    speed=speed -10;
                                }
                            }

                            terminal.setCursorPosition(pOldOld.x,pOldOld.y);
                            terminal.putCharacter(' ');
                            terminal.setCursorPosition(p.x,p.y);
                            terminal.putCharacter(p.player);
                            terminal.flush();

                    }

                }
                    if (p.x == pOld.x && p.y == pOld.y) {
                        GameOver.gameOver(terminal);

                    }
                    Thread.sleep(5);
                    keyStroke = terminal.pollInput();


            } while (keyStroke == null) ;



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
