package battleship2player;

// Name: M. Dallan
// Date: February 16, 2019
// Updated: February 17, 2019

// Status: finished

import java.util.Scanner;
import java.util.Arrays;

//WARNING!
//  When Mia created this program, she had a 2D array set up of (row, column), 
//  but then she gives user output as (column, row) because she is sane and is
//  consistant with cartesian planes. Be careful when editing!!!

public class BattleShip2Player 
{
    //member variables
    public static final Scanner user = new Scanner(System.in);
   
    public static final String SHIP = mymethods.Console.RED + "*" + 
            mymethods.Console.RESET;
    public static final String WATER = mymethods.Console.BLUE + "~" + 
            mymethods.Console.RESET;
    public static final String HIT = mymethods.Console.RED + "HIT" + 
            mymethods.Console.RESET;
    public static final String MISS = mymethods.Console.BLUE + "MISS" + 
            mymethods.Console.RESET;
    
    public static String playGame(String play)
    {
        Player player1 = new Player();
        Player player2 = new Player();
        int winner = 0;
        
        System.out.println();
        System.out.print(mymethods.Console.GREENBKG + "Player 1 enter name:" + 
                mymethods.Console.RESET + " ");
        player1.setName(user.nextLine());
        System.out.print(mymethods.Console.CYANBKG + "Player 2 enter name:" + 
                mymethods.Console.RESET + " ");
        player2.setName(user.nextLine());
        
        placeAllShips(player1, mymethods.Console.GREENBKG, mymethods.Console.GREEN);
        placeAllShips(player2, mymethods.Console.CYANBKG, mymethods.Console.CYAN);
        
        for(int i=0; i<20; i++) System.out.println();
        
        for(int i=0; i<200 && winner==0; i++)
        {
            winner = playRound(player1, player2.getSeaMap(), 
                    mymethods.Console.GREEN, mymethods.Console.GREENBKG, winner);
            if(winner==0) winner = playRound(player2, player1.getSeaMap(), 
                    mymethods.Console.CYAN, mymethods.Console.CYANBKG, winner);
        }
        
        if(winner==-1) System.out.println("\n" + player2.getName() + " gave up.\n" 
                + mymethods.Console.YELLOWBKG + player1.getName() + " wins!" 
                + mymethods.Console.RESET);
        else if(winner==-2) System.out.println("\n" + player2.getName() + " gave up.\n" 
                + mymethods.Console.YELLOWBKG + player1.getName() + " wins!" 
                + mymethods.Console.RESET);
        
        System.out.print("\nPlay again (y/n)? ");
        play = user.nextLine().toLowerCase().trim().substring(0,1);
        
        return play;
    }
    
    public static int playRound(Player player, String[][] seaMap, 
            String colour, String bkg, int winner)
    {
        String strike = "";
        String okp = "";
        char strikeCC = ' ';
        char strikeRC = ' ';
        int strikeR = -1;
        int strikeC = -1;
        
        for(int i=0; i<5; i++) System.out.println();
        System.out.print(bkg + player.getName());
        
        for(int i=0; i<(33-player.getName().length()); i++) System.out.print(" ");
        System.out.print(mymethods.Console.RESET + "\n");
        
        displayMap(player.getEnemyMap(), colour);
        
        //get coordinates
        while(strikeC>9 || strikeC<0 || strikeR>9 || strikeR<0 || 
                !player.getEnemyMap()[strikeR][strikeC].equals(" "))
        {
            System.out.println(bkg + "STRIKE:" + 
                    mymethods.Console.RESET);
            System.out.print("Column Row (* to quit): ");    
            strike = user.nextLine().trim().replaceAll("\\s", "");
            
            if(!strike.equals("*"))
            {
                if(strike.length()!=2)
                    System.out.println("\tInvalid entry, enter again.");
                else
                {
                    strikeCC = strike.charAt(0);
                    strikeRC = strike.charAt(1);

                    strikeC = Character.getNumericValue(strikeCC);
                    strikeR = Character.getNumericValue(strikeRC);

                    if(strikeC>9 || strikeC<0 || strikeR>9 || strikeR<0)
                       System.out.println("\tInvalid entry, enter again.");
                    else if(!player.getEnemyMap()[strikeR][strikeC].equals(" "))
                       System.out.println("\tMissile previously launched at coordinate."
                            + "\n\tStrike a new coordinate.");
                }
            }
            else
            {
                strikeC = 0;
                strikeR = 0;
                if(colour.equals(mymethods.Console.GREEN)) winner = -2;
                else winner = -1;
            }
        }
        
        //hit or miss
        if(!seaMap[strikeR][strikeC].equals(" ") && winner==0) 
        {
            System.out.println("\n\n\n\n");
            System.out.print(mymethods.Console.REDBKG + "(" + strikeC + ", " + 
                    strikeR + ")" + mymethods.Console.RESET);
            System.out.println(" " + HIT);
            player.getEnemyMap()[strikeR][strikeC] = SHIP;
            
            switch(seaMap[strikeR][strikeC])
            {
                case "A": 
                {
                    player.setA(player.getA()+1); 
                    if(player.getA()==5) 
                    {
                        System.out.println(mymethods.Console.RED + 
                            "Aircraft Carrier sunk!" + mymethods.Console.RESET);
                        player.setShipsSunk(player.getShipsSunk()+1);
                    }
                    break;
                }
                case "B": 
                {
                    player.setB(player.getB()+1); 
                    if(player.getB()==4) 
                    {
                        System.out.println(mymethods.Console.RED + 
                            "Battleship sunk!" + mymethods.Console.RESET);
                        player.setShipsSunk(player.getShipsSunk()+1);
                    }
                    break;
                }
                case "C": 
                {
                    player.setC(player.getC()+1); 
                    if(player.getC()==3) 
                    {
                        System.out.println(mymethods.Console.RED + 
                            "Cruiser sunk!" + mymethods.Console.RESET);
                        player.setShipsSunk(player.getShipsSunk()+1);
                    }
                    break;
                }
                case "S": 
                {
                    player.setS(player.getS()+1); 
                    if(player.getS()==3) 
                    {
                        System.out.println(mymethods.Console.RED + 
                            "Submarine sunk!" + mymethods.Console.RESET);
                        player.setShipsSunk(player.getShipsSunk()+1);
                    }
                    break;
                }
                case "D": 
                {
                    player.setD(player.getD()+1); 
                    if(player.getD()==2) 
                    {
                        System.out.println(mymethods.Console.RED + 
                            "Destroyer sunk!" + mymethods.Console.RESET);
                        player.setShipsSunk(player.getShipsSunk()+1);
                    }
                }       
            }
        }
        else if(winner==0)
        {
            System.out.println("\n\n\n\n");
            System.out.print(mymethods.Console.BLUEBKG + 
                    mymethods.Console.WHITE + "(" + strikeC + ", " + strikeR + 
                    ")" + mymethods.Console.RESET);
            System.out.println(" " + MISS);
            player.getEnemyMap()[strikeR][strikeC] = WATER;
        }
        
        if(winner==0) 
        {
            player.setMissiles(player.getMissiles()+1);
            displayMap(player.getEnemyMap(), colour);
        }
        
        if(player.getShipsSunk()==5 && winner==0) 
        {
            System.out.println(mymethods.Console.RED + "You have sunk "
                + "all of the ships!" + mymethods.Console.RESET);
            
            System.out.println(mymethods.Console.YELLOWBKG + player.getName() 
                    + " wins!" + mymethods.Console.RESET);
            System.out.println("Missiles launched: " + player.getMissiles());
            
            winner = 1;
        }
        
        System.out.print("Enter to continue...");
        okp = user.nextLine();
        
        return winner;
    }
    
    public static void displayMap(String[][] map, String colour)
    {
        System.out.println(colour + 
            "  0  1  2  3  4  5  6  7  8  9" + mymethods.Console.RESET);
        
        for(int row=0; row<10; row++)
            System.out.println(colour + row + 
                    mymethods.Console.RESET + Arrays.toString(map[row]));
        
        System.out.println();
    }
    
    public static void placeAllShips(Player player, String bkg, String colour)
    {
        String ship = "";
        int length = 0;
        String letter = "";
        
        for(int i=0; i<20; i++) System.out.println();
        System.out.println(bkg + player.getName() + 
                mymethods.Console.RESET + " privately place ships.");
        
        for(int i=1; i<6; i++)
        {
            switch(i)
            {
                case 1:
                    ship = mymethods.Console.RED + "Aircraft Carrier *****" + 
                            mymethods.Console.RESET;
                    length = 5;    
                    letter = "A";
                    break;
                case 2:
                    ship = mymethods.Console.RED + "Battleship        ****" + 
                            mymethods.Console.RESET;
                    length = 4;
                    letter = "B";
                    break;
                case 3:
                    ship = mymethods.Console.RED + "Cruiser            ***" + 
                            mymethods.Console.RESET;
                    length = 3;
                    letter = "C";
                    break;
                case 4:
                    ship = mymethods.Console.RED + "Submarine        ***" + 
                            mymethods.Console.RESET;
                    length = 3;
                    letter = "S";
                    break;
                case 5:
                    ship = mymethods.Console.RED + "Destroyer        **" + 
                            mymethods.Console.RESET;
                    length = 2;
                    letter = "D";
            }
            
            placeShip(player, colour, ship, length, letter);
        }
    }
    
    public static void placeShip(Player player, String colour, String ship, 
            int length, String letter)
    {
        String vORh = "";
        String place = "";
        String confirm = "";
        char placeCC = ' ';
        char placeRC = ' ';
        int placeC = -1;
        int placeR = -1;
        int placeable = 0;
        
        for(int i=0; i<10; i++) System.out.println();
        System.out.println("\n" + ship);
        
        displayMap(player.getSeaMap(), colour);
        
        while(!(vORh.equals("v") || vORh.equals("h")))
        {
            System.out.print("Vertical(v) or horizontal(h)? ");
            vORh = user.nextLine().trim().toLowerCase().substring(0,1);
        }
        
        switch(vORh)
        {
            case "v":
                while(placeC>9 || placeC<0 || placeR>(10-length) || placeR<0 || 
                        !confirm.equals("y"))
                {
                    System.out.println(mymethods.Console.YELLOWBKG + 
                        "Enter topmost coordinate:" + mymethods.Console.RESET);
                    
                    System.out.print("Column Row: ");
                    place = user.nextLine().trim().replaceAll("\\s", "");

                    if(place.length()!=2)
                        System.out.println("\tInvalid entry, enter again.");
                    else
                    {
                        placeCC = place.charAt(0);
                        placeRC = place.charAt(1);

                        placeC = Character.getNumericValue(placeCC);
                        placeR = Character.getNumericValue(placeRC);

                        if(placeC>9 || placeC<0 || placeR>(10-length) || placeR<0)
                           System.out.println("\tInvalid entry, enter again.");
                        else
                        {
                            for(int i=0; i<length; i++)
                            {
                                if(player.getSeaMap()[placeR + i][placeC].equals(" ")) 
                                    placeable++;
                            }
                            
                            if(placeable==length)
                            {
                                for(int i=0; i<length; i++)
                                    player.getSeaMap()[placeR + i][placeC] = letter;
                                
                                System.out.println();
                                displayMap(player.getSeaMap(), colour);
                                
                                System.out.print("Confirm (y/n): ");
                                confirm = user.nextLine().trim().toLowerCase().substring(0,1);
                                
                                if(!confirm.equals("y"))
                                {
                                    for(int i=0; i<length; i++)
                                        player.getSeaMap()[placeR + i][placeC] = " ";
                                }
                            }
                            else
                            {
                                System.out.println("\tShip cannot be placed, "
                                        + "enter again.");
                                placeC = -1;
                                placeR = -1;
                            }
                            
                            placeable = 0;
                        }
                    }
                }
                
                break;
            case "h":
                while(placeC>(10-length) || placeC<0 || placeR>9 || placeR<0 || 
                        !confirm.equals("y"))
                {
                    System.out.println(mymethods.Console.YELLOWBKG + 
                        "Enter leftmost coordinate:" + mymethods.Console.RESET);
                    
                    System.out.print("Column Row: ");
                    place = user.nextLine().trim().replaceAll("\\s", "");

                    if(place.length()!=2)
                        System.out.println("\tInvalid entry, enter again.");
                    else
                    {
                        placeCC = place.charAt(0);
                        placeRC = place.charAt(1);

                        placeC = Character.getNumericValue(placeCC);
                        placeR = Character.getNumericValue(placeRC);

                        if(placeC>(10-length) || placeC<0 || placeR>9 || placeR<0)
                           System.out.println("\tInvalid entry, enter again.");
                        else
                        {
                            for(int i=0; i<length; i++)
                            {
                                if(player.getSeaMap()[placeR][placeC + i].equals(" ")) 
                                    placeable++;
                            }
                            
                            if(placeable==length)
                            {
                                for(int i=0; i<length; i++)
                                    player.getSeaMap()[placeR][placeC + i] = letter;
                                
                                System.out.println();
                                displayMap(player.getSeaMap(), colour);
                                
                                System.out.print("Confirm (y/n): ");
                                confirm = user.nextLine().trim().toLowerCase().substring(0,1);
                                
                                if(!confirm.equals("y"))
                                {
                                    for(int i=0; i<length; i++)
                                        player.getSeaMap()[placeR][placeC + i] = " ";
                                }
                            }
                            else
                            {
                                System.out.println("\tShip cannot be placed, "
                                        + "enter again.");
                                placeC = -1;
                                placeR = -1;
                            }
                            
                            placeable = 0;
                        }
                    }
                }     
        }
    }
    
    public static void main(String[] args) 
    {
        String play = "";
        
        System.out.println("Welcome to Battleship 2 Player!");
        
        //display ships
        System.out.println(mymethods.Console.REDBKG  + "\nSHIPS:" + mymethods.Console.RESET
        + "\nAircraft Carrier " + mymethods.Console.RED + "*****" + mymethods.Console.RESET
        + "\nBattleship       " + mymethods.Console.RED + "****" + mymethods.Console.RESET
        + "\nCruiser          " + mymethods.Console.RED + "***" + mymethods.Console.RESET
        + "\nSubmarine        " + mymethods.Console.RED + "***" + mymethods.Console.RESET
        + "\nDestroyer        " + mymethods.Console.RED + "**" + mymethods.Console.RESET);
        
        System.out.print("\nPlay game (y/n)? ");
        play = user.nextLine().toLowerCase().trim().substring(0,1);
        
        while(play.equals("y")) play = playGame(play);
        
        System.out.println("\n\nGoodbye!");
    }
    
}
