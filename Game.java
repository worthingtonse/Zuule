import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is the main class of the "Zuul" application.
 * "Zuul" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * - A level can be chosen, which determine the number of moves a player can have.
 * - The trap door will slows the game if the user tries to go through it.
 * - To access the last door, the player must take the key placed in the "DELIVERY_ROOM"
 * - A beamer can be used to teleport the player wherever he had chosen
 * - The room "TOILETS" randomly teleports the player into another.
 * - To win the game, you have to reach the room "OUTSIDE".
 * 
 * @author Michael Kolling and David J. Barnes and Alexandre Boursier and Nolan Potier
 * @version 2011.10.24
 */

public class Game {
    private Parser parser;
    private static Player player;
    // Count the number of current number of moves
    private static int numberOfMoves;
    // Fix a limit to the number of moves
    private static int limitOfMoves;
   
    // Build a list which contains all the current rooms of the game
    private static ArrayList<Room> rooms;

 
    private HashMap<String, Item> items;
    private ArrayList<Door> doors;

    private Room entrance;
    private Room pit_room;
    private Room corridor;
    private Room teleport;
    private Room teleporter;
    private Room fountain;
    private Room t_hall;
    private Room trap_room;
    private Room treasure;
    private Room crypt;
    private Room landing_room;
    private Room outside;

    /**
     * Create the game and initialize its internal map.
     */
    public Game() {
        rooms = new ArrayList<Room>();
        items = new HashMap<String, Item>();  
        doors = new ArrayList<Door>();
        numberOfMoves = 0;

        createItems();
        createDoors();        
        setPlayer(new Player());
        createRooms();
        setRoomsDoors();
        addItemsToRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create a random trap door to make the game harder.
     * 
     */
    private void createRooms() {

        // Create the rooms
        entrance = new Room("near a teleporter with a sign above that reads\n \"Portal to Zuule.\" You enter the portal...", Type.ENTRANCE);
        
        
        
        
        
        String pitRoomImage = "in a room that has a large pit in the center.\r\n";
        pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆ\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆ\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";
pitRoomImage +="â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘â–‘\r\n";

        
        pit_room = new Room( pitRoomImage, Type.PIT_ROOM);
        corridor = new Room("in the corridor. There is a door to the north, stairs going down \n to the east and a open hall to the south.", Type.CORRIDOR);
        teleport = new Room("You are in a room with a teleporter located on the west wall.", Type.LANDING_ROOM);
        teleporter = new Room("You are telported to room with four doors and a ladder going up and down. A sign points down \"Treasure room\".", Type.TELEPORTER);
        fountain = new Room("in a large room with a fountain. There is a key in the fountain.", Type.FOUNTAIN);
        t_hall = new Room("in a hall that goes to a T. Stench comes from the west. Green glow from the east.", Type.T_HALL);
        trap_room = new Room("Empty Room with a opening to the east.", Type.TRAP_ROOM);
        treasure = new Room("in the tresure room. Mounds of gold fill the floor. \n There is ladder going up.", Type.TREASURE);
        crypt = new Room("in a crypt. Four coffins are in the room.", Type.CRYPT);
        landing_room = new Room("The floor glows green with magic symbols.", Type.LANDING_ROOM);
        outside = new Room("You are outside!.", Type.OUTSIDE);
        
        //Create character
        
        String ocrImage = "";
        ocrImage += ("\r\n ");
        ocrImage += ("\r\n");
        ocrImage += ("Orc\r\n");
        ocrImage += ("                _,.---''```````'-.\r\n");
        ocrImage += ("            ,-'`                  `-._\r\n");
        ocrImage += ("         ,-`                   __,-``,\\\r\n");
        ocrImage += ("        /             _       /,'  ,|/ \\\r\n");
        ocrImage += ("      ,'         ,''-<_`'.    |  ,' |   \\\r\n");
        ocrImage += ("     /          / _    `  `.  | / \\ |\\  |\r\n");
        ocrImage += ("     |         (  |`'-,---, `'  \\_|/ |  |\r\n");
        ocrImage += ("     |         |`  \\  \\|  /  __,    _ \\ |\r\n");
        ocrImage += ("     |         |    `._\\,'  '    ,-`_\\ \\|\r\n");
        ocrImage += ("     |         |        ,----      /|   )\r\n");
        ocrImage += ("     \\         \\       / --.      {/   /|\r\n");
        ocrImage += ("      \\         | |       `.\\         / |\r\n");
        ocrImage += ("       \\        / `-.                 | /\r\n");
        ocrImage += ("        `.     |     `-        _,--V`)\\/        _,-\r\n");
        ocrImage += ("          `,   |           /``V_,.--`  \\.  _,-'`\r\n");
        ocrImage += ("           /`--'`._        `-'`         )`'\r\n");
        ocrImage += ("          /        `-.            _,.-'`\r\n");
        ocrImage += ("                      `-.____,.-'`\r\n");

        
        
        Character Orc = new Character("Half Dead Orc on the ground", ocrImage + "I threw my key in the fountain for good luck. Then I tripped on the stairs. I think I broke my back.  ");
        Character OrcStoned = new Character("You find an orc laying on the ground", ocrImage + "Don't eat the mushrooms man!");

        corridor.addCharacter(Orc);
        crypt.addCharacter(OrcStoned);

        // start game in the bedroom
        getPlayer().setCurrentRoom(entrance); 
       
    }
    
    /**
     * Initialise items
     */
    private void createItems() {
        Item key;
        key = new Item("Key", "This key can open a door...");
        items.put(key.getName().toLowerCase(), key);
    }

    /**
     * Initialise room doors and respective locks
     */
    private void setRoomsDoors()
    {        
        // Initialise room exits
        entrance.setDoor("east", pit_room, false);

        pit_room.setDoor("east", corridor, false);
        pit_room.setDoor("west", entrance, false);

        corridor.setDoor("north", t_hall, true);
        corridor.setDoor("east", fountain, false);
        corridor.setDoor("south", teleport, false);
        corridor.setDoor("west", pit_room, false);
        
        fountain.setDoor("west", corridor, false);

        teleport.setDoor("west", teleporter, false);
        teleport.setDoor("north", corridor, false);
   
        t_hall.setDoor("east", trap_room, false);
        t_hall.setDoor("west", crypt, false);
        t_hall.setDoor("south", corridor, false);     

        crypt.setDoor("east", t_hall, false);
        
        trap_room.setDoor("west", t_hall, false);
        trap_room.setDoor("east", landing_room, true);

        landing_room.setDoor("east", treasure, false);
        landing_room.setDoor("west", trap_room, false);
        
        treasure.setDoor("west", landing_room, false);
        treasure.setDoor("up", outside, false);
        
        teleporter.setDoor("west", t_hall, false);
        teleporter.setDoor("east", entrance, false);
        teleporter.setDoor("up", pit_room, false);
        teleporter.setDoor("down", treasure, false);
        teleporter.setDoor("north", t_hall, false);
        teleporter.setDoor("south", crypt , false);
    }

    /**
     * Create the Doors for the game.
     */
    private void createDoors(){
        Door north, east, south, west,up, down;

        north = new Door("north");
        east = new Door("east");
        south = new Door("south");
        west = new Door("west");
        // To get out of the trap
        up = new Door("up");
        down = new Door("down");

        //add each door to doors collection
        doors.add(north);
        doors.add(east);
        doors.add(south);
        doors.add(west);
        doors.add(up);
        doors.add(down);
    }

    /**
     * Add items to the rooms
     */
    private void addItemsToRooms(){
        fountain.addItem(items.get("key"));
    }

    /**
     * Adding a room to the dictionary
     * @param r
     */
    public static void addRoom(Room r){
        rooms.add(r);
    }   

    /**
     * Main play routine. Loops until end of play.
     * 
     */
    public void play() {

        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while(! finished) {
            Command command = parser.getCommand();
            if(command == null) {
                System.out.println("I don't understand...");
            } else {
                finished = command.execute(getPlayer());
            }
        }
    }

    /**
     * Print out the opening message for the player. 
     * New form of time limit : a level is asked at the beginning
     * of the game defined by the maximum tolerated number of moves.
     * @return 
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Zuul!");
        
        chooseLevel();

        System.out.println("Type help if you need help.");
        System.out.println();
        System.out.println(getPlayer().getCurrentRoom().getLongDescription());

        // Instantiate a parser which will read the command words
        parser = new Parser();
    }

    /**
     * Choosing the level of the game :
     * - Easy is for beginners 
     * - Medium brings a little bit more challenge
     * - Hard is the "no-mistake way"
     * 
     */
    private void chooseLevel()
    {
        // Choosing a level (asking to the user through the terminal)
        Scanner reader = new Scanner(System.in);
        System.out.println("Please choose a level 1-3 ");
        System.out.println("1. Easy 20 moves");
        System.out.println("2. Medium 16 moves");
        System.out.println("3. Hard 14 moves");
        // Find the chosen level and alter the number of moves accorfing to the chosen one
        try {
            switch (reader.nextInt()) {
            case 0:
                limitOfMoves = 20;
                System.out.println("You've chosen the easy way to win ! - Number of moves : " + limitOfMoves);
                break;
            case 1:
                limitOfMoves = 16;
                System.out.println("You've chosen the medium level :)- Number of moves : " + limitOfMoves);
                break;
            case 2:
                limitOfMoves = 14;
                System.out.println("It's gonna be hard this way :@  - Number of moves : " + limitOfMoves);
                break;
            default:
                limitOfMoves = 20;
                System.out.println("Unkown command - Default level : Easy - Number of moves : " + limitOfMoves);
                break;
            }
        } catch(Exception e){
            limitOfMoves = 20;
            System.out.println("Unkown command - Default level : Easy - Number of moves : " + limitOfMoves);
        }
    }

    /**
     * Counting the current move of the player
     * @return false if the player has executed too many moves, true otherwise
     */
    public static boolean countMove(){
        // Count a move
        numberOfMoves++;

        // Give some informations concerning the number of moves
        if (numberOfMoves < limitOfMoves) {
            System.out.println("Current number of moves : " + numberOfMoves);
            System.out.println("Moves left : " + (limitOfMoves - numberOfMoves));
            return false;
            // Ending the game if the number of moves is reached
        } else {
            System.out.println("You have reached the maximum number of moves");
            System.out.println("By the way, GAME OVER ! ");
            System.out.println();
            System.out.println();
            return true;
        }
    }


    /**
     * @return the numberOfMoves
     */
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    /**
     * @return the limitOfMoves
     */
    public int getLimitOfMoves() {
        return limitOfMoves;
    }

    /**
     * @return the rooms
     */
    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * @param limitOfMoves the limitOfMoves to set
     */
    public void setLimitOfMoves(int lom) {
        limitOfMoves = lom;
    }




    /**
     * @return the player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public static void setPlayer(Player player) {
        Game.player = player;
    }

}
