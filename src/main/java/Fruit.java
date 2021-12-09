import java.util.concurrent.ThreadLocalRandom;

public class Fruit {
int x;
int y;
final char fruit='0';

    public Fruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void newFruit(){
        int randomNumber = ThreadLocalRandom.current().nextInt(1,40);
        x=randomNumber; y=randomNumber;



    }


}
