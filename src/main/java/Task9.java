import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Task9 {

        public static void main(String[] args) throws Exception {


            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = terminalFactory.createTerminal();
            terminal.setCursorVisible(false);


            boolean continueReadingInput=true;
            int x = 15;
            int y = 10;
            int oldX=x;
            int oldY=y;
            int oldOldX=oldX;
            int oldOldY=oldY;

            int xMonster= 20;
            int yMonster=15;
            final char player = 'X';
            final char monster = '\u0394';
            KeyType latestType = null;

            terminal.setCursorPosition(x,y);
            terminal.putCharacter(player);


            terminal.setCursorPosition(xMonster,+yMonster);
            terminal.putCharacter(monster);


            while (continueReadingInput) {

                int index = 0;

                KeyStroke keyStroke = null;
                do {
                    index++;
                    if (index %50 == 0){
                        if(latestType != null){
                            oldOldX = oldX;
                            oldOldY = oldY;
                            oldX = x;
                            oldY = y;



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
                            terminal.setCursorPosition(oldOldX,oldOldY);
                            terminal.putCharacter(' ');
                            terminal.setCursorPosition(x,y);
                            terminal.putCharacter(player);
                            terminal.flush();

                        }

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

        }}
