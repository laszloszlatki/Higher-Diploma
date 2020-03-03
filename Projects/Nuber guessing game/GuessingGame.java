package guessinggame;
 /*
 * @author: Laszlo Szlatki
 * @date: 03/01/2020
 */
/** 
 * Java game “Guess a Number” that allows user to guess a random number that has been generated.
 * The system or computer will generate a random number from 1 to 100.
 * A dialogue box is displayed where user is asked to enter their guess number.
 * Computer tells if the guess number matches or it is higher/lower than the one it generated.
 * The game continues until the user guesses the computer number.
 */
 
import javax.swing.*;
 
public class GuessingGame {
    public static void main(String[] args) {
        int computerNumber = (int) (Math.random()*100 + 1);
        int userAnswer = 0;
        System.out.println("The correct guess would be " + computerNumber);
        int count = 1;
        while (userAnswer != computerNumber)
        {
            String response = JOptionPane.showInputDialog(null, 
                "Enter a guess between 1 and 100", "Guessing Game", 3);
            userAnswer = Integer.parseInt(response);
            JOptionPane.showMessageDialog(null, ""+ determineGuess(userAnswer, computerNumber, count)); 
            count++;
        }  
    }
    
    public static String determineGuess(int userAnswer, int computerNumber, int count){
        if (userAnswer <=0 || userAnswer >100) {
            return "Your guess is invalid";
        }
        else if (userAnswer == computerNumber ){
            return "Correct!\nTotal Guesses: " + count;
        }
        else if (userAnswer > computerNumber) {
            return "Your guess is too high, try again.\nTry Number: " + count;
        }
        else if (userAnswer < computerNumber) {
            return "Your guess is too low, try again.\nTry Number: " + count;
        }
        else {
            return "Your guess is incorrect\nTry Number: " + count;
            
        }
    }
}