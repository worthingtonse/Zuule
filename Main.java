import java.util.Scanner;
/**
 * Main class dedicated to start and restart the game
 * @author Alexandre Boursier and Nolan Potier
 * @version 2011.10.24
 * @version 2014.03.01
 */
public class Main {
    public static void main(String[] args) {
        Game G;
        boolean play_again = true;// Allows the player to play several times
        while (play_again)
        {
            System.out.println("");
            
      System.out.println(" ");
            System.out.println("â•”â•â•â•â•â•—â”€â”€â”€â”€â”€â•”â•—");
            System.out.println("â”€â”€â•”â•â•”â•¬â•—â•”â•¦â•—â•”â•£â•‘â•”â•â•â•—");
            System.out.println("â”€â•”â•â•”â•â•‘â•‘â•‘â•‘â•‘â•‘â•‘â•‘â•‘â•‘â•â•£");
            System.out.println("â•”â•â•â•šâ•â•£â•šâ•â•‘â•šâ•â•‘â•šâ•£â•‘â•â•£");
            System.out.println("â•šâ•â•â•â•â•©â•â•â•©â•â•â•©â•â•©â•â•â•");
            System.out.println("Only a fool would go to Zuule");         
     System.out.println("                  ______ ");
     System.out.println("               .-\"      \"-. ");
     System.out.println("              /            \\ ");
     System.out.println("  _          |              |          _ ");
     System.out.println(" ( \\         |,  .-.  .-.  ,|         / )");
     System.out.println("  > \"=._     | )(__/  \\__)( |     _.=\" <");
     System.out.println(" (_/\"=._\"=._ |/     /\\     \\| _.=\"_.=\"\\_)");
     System.out.println("        \"=._ (_     ^^     _)\"_.=\" ");
     System.out.println("            \"=\\__|IIIIII|__/=\"");
     System.out.println("           _.=\"| \\IIIIII/ |\"=._");
     System.out.println(" _     _.=\"_.=\"\\          /\"=._\"=._     _");
     System.out.println("( \\_.=\"_.=\"     `--------`     \"=._\"=._/ )");
     System.out.println(" > _.=\"                            \"=._ <");
     System.out.println("(_/   jgs                              \\_)");
          
            
           
            
            G = new Game();
            G.play();
        }//End While
        System.out.println("Thank you for playing Zuule. Goodbye.");
    }//End main
}//End Zuule
