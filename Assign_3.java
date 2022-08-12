import java.util.Scanner;

public class Assign_3{
	public static void main(String[] args){ 

		Scanner input = new Scanner (System.in);
		System.out.println("Enter a board dimension size (4-10):  ");
		int temp_dim = input.nextInt();
		System.out.println("Enter a ship  size (2-4):  ");
		int temp_ship = input.nextInt();

		int easy = (int) ((temp_dim*temp_dim)*.7); // create guess numbers for easy medium and hard settings based on the ratios to the total number of possible shots that can be taken

		int med = (int) ((temp_dim*temp_dim)*.5);

		int hard = (int) ((temp_dim*temp_dim)*.3);

		System.out.println("Choose a difficulty, enter E for Easy ("+ easy +" guesses), M for Medium ("+ med + " guesses), and"+
		" H for Hard ("+ hard + " guesses).");
		String temp_diff = input.next();
		int difficulty = 0;

		if (temp_diff.equals("E"))  //set the difficulty to the input of the user
			difficulty = easy;

		else if (temp_diff.equals("M"))
			difficulty = med;

		else 
			difficulty = hard;






		final int dimension = temp_dim; // finalize the board size
		final int ship_size = temp_ship; // finalize the ship size
		Boolean game = true;

		String hit_miss = "";

		int total = 0;
		int hits = 0;


		String[][] player_array = new String[dimension][dimension]; 


		for (int j = 0; j < player_array.length; j++){
			for(int m = 0; m<player_array[j].length; m++){
				player_array[j][m] = "|   ";
			}}


		String[][] ship = Get_Admin_Board(dimension, ship_size);

		int x = (dimension*dimension); // determine how many boxes there will be in order to track for the data_validation method

		String[] check = new String[x]; //initialize the checking array

		for (int m = 0; m<check.length; m++){
			check[m] = " ";
		}


		int check_counter = 0;

		while(game == true){


		String guess = data_validation(dimension, check, check_counter);


		check_counter++; //increase the check counter so that the next coordinates get put into the next spot in the array check.


		int letter = ((guess.charAt(0))-'A');
		int num = ((guess.charAt(1))-'0');

		if (ship[num][letter ]== "| X "){ // update the score.
			hit_miss = "X";
			hits++;
			total++;
		}

		else{
			hit_miss = "#";
			total++;
		}
		System.out.println("");
		System.out.println("");

		System.out.println("Total shots taken: " + total + "        Total shots hit: " + hits); // update the board total and hit values
		System.out.println("");

		Get_Player_Board(dimension, ship_size, num, letter, hit_miss, player_array);

		if (hits == ship_size || total == difficulty) //end the game when all the spaces of the ship have been hit, or when the player reaxhes their max guesses
			game = false;
		else
			game = true;
	
		
	}

	System.out.println("");

	if (hits == ship_size) // inform player if they won based on whether they made all possible hits
			System.out.println("You Win!");
	else
			System.out.println(" You Lose :(");

	System.out.println("");



	
	System.out.println(" S C O R E "); //print out the score
	System.out.println("");
	System.out.println("Total Shots: " + total);
	System.out.println("Total Misses: "+ (total-hits));
	System.out.println("Total Hits: "+ hits);



		//generate_ship(ship_size, dimension);
		System.out.println("");
		System.out.println("");
		//data_validation(dimension);
	}


	public static String[][] Get_Admin_Board(int dimension, int ship_size){ //Method for initializing the board
		 letters(dimension);
		 System.out.println("");
		 non_alter_line(dimension);
		 System.out.println("");
		 String[][] ship = admin_alter_line(dimension, ship_size);
		 return ship;
	}
	public static void Get_Player_Board(int dimension, int ship_size, int num1, int num2, String hit_miss, String[][] player_array){

		letters(dimension);
		 System.out.println("");
		 non_alter_line(dimension);
		 System.out.println("");
		 player_alter_line(dimension, ship_size, num1, num2, hit_miss, player_array);


	}

	public static void letters(int dimension){ // Method for initializing the letter Row
		String vari  = "   ";
		for (int i = 65; i<(65+dimension); i++){
			vari += (char)(i) + "   ";
		}
		System.out.print(vari);
	}

	public static void non_alter_line(int dimension){ // Method for the Board lines that won't be altered through the course of the game
		String line = " ";
		for (int i = 0; i < dimension; i++ )
			line += "+---";
		line += "+";
		System.out.print(line);
	}

	public static String[][] admin_alter_line(int dimension, int ship_size){ //creates the first board displayed

		String[][] ship = generate_ship(ship_size, dimension);
		
		for (int i = 0; i<dimension; i++){
			System.out.print(i);
		
			for (int j = 0; j <dimension; j++) //print the lines of the board
				System.out.print("|   ");
			System.out.println("|");
			non_alter_line(dimension);
			System.out.println("");
			}
		return ship;
		}
		public static void player_alter_line(int dimension, int ship_size, int num1, int num2, String hit_miss, String[][] player_array){
			

			player_array[num1][num2] = "| "+ hit_miss + " "; //will change depending on if the shot was a hit or a miss
		
		for (int i = 0; i<dimension; i++){
			System.out.print(i);
		
			for (int j = 0; j <dimension; j++)
				System.out.print(player_array[i][j]); //print out each element of the board
			System.out.println("|");
			non_alter_line(dimension);
			System.out.println("");

		}}

	public static String[][] generate_ship(int ship_size, int dimension){ //generates the ship on the board
		String[][] ship = new String[dimension][dimension];
		for (int j = 0; j < ship.length; j++){
			for(int m = 0; m<ship[j].length; m++){
				ship[j][m] = "|   "; //iterate throught the whole array initializing it with blank spaces
			}}
		int vert_row = 0;
		char vert_col = 'A';
		int horz_row = 0;
		char horz_col = 'A';

		int num = (int) (Math.random() * 2); //determine if ship will be horizontal or vertical



		if (num == 1){

		vert_row = (int) (Math.random() * (dimension-ship_size)) + (ship_size-1); //Generate a random number for the vertical row placement by using the dimension and ship size.

		vert_col =(char)((int)((int)'A' + Math.random() * ((((int)'J' ) - (10 - dimension)) - (int)'A' + 1))); //Generate a random number for the vertical column placement.
		int num_vert_col = (int)(vert_col) - (int)('A');


		String hit = "| X ";
		int counter = 0;
		for (int m = 0; m<ship_size; m++){
			ship[vert_row - counter][num_vert_col] = hit;
			counter+=1;
			}

		return ship; //return the ship array for the vertical ship
			
		}
		else{
		horz_row = (int) (Math.random() * dimension); //Generate a random number between 9 and 3 for the vertical possible ship
		horz_col =(char)((((int)((int)'A' + Math.random() * ((((int)'A')+(dimension-ship_size)) - (int)'A' + 1)))));// Generate a random number for the horizontal ship column placement by using the ship size and the dimension as parameters.
		int num_horz_col = ((int)(horz_col) - (int)('A'));

		String hit = "| X ";
		int counter = 0;

		for (int m = 0; m<ship_size; m++){
			ship[horz_row][num_horz_col + counter] = hit;
			counter+=1;
			}

		return ship; //return the ship array for the horizontal ship
		}		



	}


	public static String data_validation(int dimension, String[] check, int check_counter){

		String temp = Character.toString((char)(((int)'A' + dimension - 1)));

		Scanner input = new Scanner(System.in);
		System.out.print("Enter coordinates in the form of a capital letter from A - " + temp + "  (column) followed by a number (row) 0 - " + (dimension - 1) + " :   ");
		String coordinates = input.next();
		//System.out.println(coordinates);
		if (coordinates.length() > 2 || coordinates.length() < 2){
			System.out.print("Not a valid input, try again:   ");
			coordinates = input.next(); //allow user to re-enter the coordinates if they were wrong.

		}
		char letter = coordinates.charAt(0);
		char num = coordinates.charAt(1);

		int vari = 0;

		while(vari < 2){
		while ((Character.isDigit(num) == false || Character.isUpperCase(letter) == false || coordinates.length() > 2 || coordinates.length() < 2 || letter < 'A' //check to see if the coordinates have proper syntax
			|| letter > ((char) ((int)('A')+(dimension - 1)))||((int)(num) - (int)'0') > (dimension - 1))){
			//System.out.println("Your input is not valid");
			System.out.print("Coordinates must be in the form of a capital letter  A - " + temp + " (column) followed by a number (row) 0 - " + (dimension - 1)+ " ");
			coordinates = input.next(); //allow user to re-enter the coordinates if they were wrong.
			letter = coordinates.charAt(0);
			num = coordinates.charAt(1);
			vari--;
			
			}
			if(vari<0){
				vari=0;
			}
			else{
				vari++;
			}
		for(int i = 0;i < check.length;i++){
			if(check[i].equals(coordinates)){
				System.out.print("Coordinates can't be repeated: "); //check to see for repeat coordinates
				coordinates = input.next(); //allow the user to input the coordinates again if they were repeats.
				letter = coordinates.charAt(0);
				num = coordinates.charAt(1);
				vari--;
			}
		}
			if(vari<0){
				vari=0;
			}
			else{
				vari++;
			}
		}	

			check[check_counter] = coordinates;		
			return coordinates; //return the coordinates now that the data is valid.

	}
		

}

	
