package netcracker.task2;

import java.util.Scanner;

/**
 * Class of dice game.
 * It includes the array of score of players, number of players and number of dices.
 */
public class DiceGame {
    private final int[] score;
    private final int players;
    private final int diceNum;

    public DiceGame(int players, int diceNum){
        if (players == 0){
            throw new IllegalArgumentException("Illegal Number Of Players");
        }
        score = new int[players];
        this.players = players;
        this.diceNum = diceNum;
    }

    /**
     * @return max number in array score[]
     */
    public int maxCurrent(){
        int maxCurrent = 0;
        for(int j = 0; j < players; ++j){
            if(score[j]>maxCurrent)
                maxCurrent = score[j];
        }
        return maxCurrent;
    }

    /**
     * @return max number in array score[], which is lower than maxCurrent variable
     */
    public int lowerMax(int maxCurrent){
        int lowerMax = 0;
        for(int j = 0; j < players; ++j){
            if(score[j]>lowerMax && score[j]<maxCurrent)
                lowerMax = score[j];
        }
        return lowerMax;
    }

    /**
     * @return number of player, who was the winner of previous round
     */
    public int winnerOfPrev(int[] queue){
        int winnerOfPrev = 0;
        for(int i = 0; i < players; ++i){
            if(queue[i] == 0){
                winnerOfPrev = i;
            }
        }
        return winnerOfPrev;
    }

    /**
     * A round of the game
     * thisRound[1] = 4 means that Player 1 will be 5th in the next round
     * @return Queue of players for the next round
     */
    public int[] nextRound(int[] queue){
        int[] thisRound = new int[players];
        for(int i = 0; i < players; ++i){
            score[queue[i]] += (int) (Math.random() * (diceNum*5+1)) + diceNum;
        }
        int maxCurrent = maxCurrent();
        int rawPlayers = players;
        while(rawPlayers > 0){
            for(int j = 0; j < players; ++j){
                if(score[j] == maxCurrent){
                    thisRound[j] = players - rawPlayers;
                    rawPlayers --;
                    if (j == 0){
                        System.out.println(players - rawPlayers+". you - "+score[j]);
                    } else if (j == players-1){
                        System.out.println(players - rawPlayers+". computer - "+score[j]);
                    } else {System.out.println(players - rawPlayers+". player "+j+" - "+score[j]);}
                }
            }
            maxCurrent = lowerMax(maxCurrent);
        }
        return thisRound;
    }

    /**
     * The whole game
     * Includes arrays queue[] - array of queue of round ana wins[] - it counts number of wins
     * @return Number of players, who wins
     */
    public int playGame(){
        int[] queue = new int[players];
        int[] wins = new int[players];
        for(int i = 0; i < players; ++i){
            queue[i] = i;
            wins[i] = 0;
        }
        int j = 1;
        while(true){
            System.out.println("Round "+(j++));
            queue = nextRound(queue);
            wins[winnerOfPrev(queue)] +=1;
            if (wins[winnerOfPrev(queue)] == 7){
                    return winnerOfPrev(queue);
            }
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        int pl = scanner.nextInt();
        System.out.println("Enter number of dices ");
        int dices = scanner.nextInt();
        DiceGame myGame = new DiceGame(pl, dices);
        System.out.println("Payer "+ (myGame.playGame())+" wins!");
    }
}
