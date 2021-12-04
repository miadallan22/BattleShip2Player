package battleship2player;

// Name: M. Dallan
// Date: February 16, 2019

public class Player 
{
    
    private String name;
    private int shipsSunk;
    private String[][] seaMap;
    private String[][] enemyMap;
    private int a;  //Aircraft carrier
    private int b;  //Battleship
    private int c;  //Cruiser
    private int s;  //Submarine
    private int d;  //Destroyer
    private boolean quit;
    private int missiles;
    
    public Player()
    {
        name = "";
        shipsSunk = 0;
        seaMap = new String[10][10];
        enemyMap = new String[10][10];
        a = 0;
        b = 0;
        c = 0;
        s = 0;
        d = 0;
        quit = false;
        missiles = 0;
        
        //fill maps
        for(int row=0; row<10; row++)
            for(int column=0; column<10; column++)
                seaMap[row][column] = " ";
        for(int row=0; row<10; row++)
            for(int column=0; column<10; column++)
                enemyMap[row][column] = " ";
    }
    
    //mutator mehtods
    public void setName(String name)
    {
        this.name = name.trim();
    }
    
    public void setShipsSunk(int shipsSunk)
    {
        this.shipsSunk = shipsSunk;
    }
    
    public void setSeaMap(String[][] seaMap)
    {
        this.seaMap = seaMap;
    }
    
    public void setEnemyMap(String[][] enemyMap)
    {
        this.enemyMap = enemyMap;
    }
    
    public void setA(int a)
    {
        this.a = a;
    }
    
    public void setB(int b)
    {
        this.b = b;
    }
    
    public void setC(int c)
    {
        this.c = c;
    }
    
    public void setS(int s)
    {
        this.s = s;
    }
    
    public void setD(int d)
    {
        this.d = d;
    }
    
    public void setQuit(boolean quit)
    {
        this.quit = quit;
    }
    
    public void setMissiles(int missiles)
    {
        this.missiles = missiles;
    }
    
    //accsessor methods
    public String getName()
    {
        return name;
    }
    
    public int getShipsSunk()
    {
        return shipsSunk;
    }
    
    public String[][] getSeaMap()
    {
        return seaMap;
    }
    
    public String[][] getEnemyMap()
    {
        return enemyMap;
    }
    
    public int getA()
    {
        return a;
    }
    
    public int getB()
    {
        return b;
    }
    
    public int getC()
    {
        return c;
    }
    
    public int getS()
    {
        return s;
    }
    
    public int getD()
    {
        return d;
    }
    
    public boolean getQuit()
    {
        return quit;
    }
   
    public int getMissiles()
    {
        return missiles;
    }
}
