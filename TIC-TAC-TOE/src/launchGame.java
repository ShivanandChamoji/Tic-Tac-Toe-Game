import java.util.Random;
import java.util.Scanner;

class TicTacToe {
	static char[][] board;                   //creating the reference for two dimensonal array
	
	public TicTacToe()                //construcor for creating an 2D array whenever a object of  it  created
	{
		  board =new char[3][3];
		  initBoard();                 //initialze the tabel as soona sit created so in constructor
	}
	
	void initBoard()                         //creating a tabel method
	{
		for(int i=0;i<board.length;i++) 
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}
	
	static void dispBoard()
	{
		System.out.println("-------");
		for(int i=0;i<board.length;i++) 
		{
			System.out.print("|");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+"|");
			}
			System.out.println();
			System.out.println("-------");
		}
	}
	
	static void placeMark(int row,int col,char mark)
	{
		if(row>=0 && row <=2 && col>=0 && col <=2)
		{
			board[row][col]=mark;
		}
		else
		{
			System.out.println("Invalid Position");
		}
	}
	
	 static boolean checkColWin()
	{
		for(int j=0;j<=2;j++)
		{
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
			{
				return true;
			}
			 
		}
		return false;
	}
	
	 static boolean checkRowWin()
	 {
		 	for(int i=0;i<=2;i++)
			{
				if(board[i][0]!=' '&& board[i][0]==board[i][1] && board[i][1]==board[i][2])
				{
					return true;
				}
				 
			}
			return false;
		 
	 }
	 
	 static boolean checkDiagonalWin()
	 {
		 if(board[0][0]!=' ' && board[0][0]==board[1][1] &&   board[1][1]==board[2][2] ||board[0][2]!=' '&& board[0][2]==board[1][1] && board[1][1]==board[2][0])
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
	  
	 
}

abstract class player
{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValidMove(int row,int col)
	{
		if(row>=0 && row <=2  &&
			col >= 0 && col<=2)
		{
			if(TicTacToe.board[row][col]==' ')
			{
				return true;
			}
		}
		return false;
	}
}

class AIPlayer extends player
{
	 
	
	AIPlayer(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		 do
		 {
			  Random r=new Random();                  //call random call to generate random nor
			  row=r.nextInt(3);                      //nextint() is method in random that generate random nor
			  col=r.nextInt(3);
			  
		 }while(!isValidMove(row,col));                           //checking for not true (!)
		 
		 TicTacToe.placeMark(row, col, mark);
	}
	
	 
}


class HumanPlayer extends player
{
	 
	HumanPlayer(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		 do
		 {
			 System.out.println("Enter the row and col");
				row=scan.nextInt();
				col=scan.nextInt();
		 }while(!isValidMove(row,col));                           //checking for not true (!)
		 
		 TicTacToe.placeMark(row, col, mark);
	}
	
	 
}



public class launchGame{
	public static void main(String[] args) {
		TicTacToe t =new TicTacToe();
		HumanPlayer p1=new HumanPlayer("Bob",'x');
		 AIPlayer p2=new AIPlayer("Tony",'o');
		
		player cp;                                           // loose coupling parent type reference to child
		cp=p1;                                                   //reference type initialition
		
		 while(true)                                             //loop to repeat till some one wins
		 {
			 System.out.println(cp.name +" turn");
				cp.makeMove(); 
				TicTacToe.dispBoard();                              //display board evert time
				
				if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin())  //check if someone wins every move
				{
					System.out.println(cp.name +" has won");
					break;                                       //if someone wins come out
				}
				else
				{
					if(cp==p1)                                             //change of turns
					{
						cp=p2;
					}
					else
					{
						cp=p1;
					}
				}
		 }
				
	
}
}



