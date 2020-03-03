package game;

/*
 * should have 1 variable:
 * - faceUp – an integer;
 * should have 2 methods:
 * - rollDice; /* returning a random value 1-6, stored in faceUp
 * - reportDice; /* reports the dice's current value: faceUp
 * */

public class Dice {

    private int faceUp;
    
    public int getFaceUp() {
        return faceUp;
    }
    
    private void setFaceUp(int faceUp){
    	this.faceUp = faceUp;
    }

    public int rollDice() {

        setFaceUp( (int) (Math.random() * 6 + 1));
        return faceUp;
    }

    /*
     * public void rollDice() {
            Random random = new Random();
            setFaceUp(random.nextInt(6) + 1);
        }
    */
    
    public void reportDice() {
        System.out.print(faceUp + " ");
    }
}
