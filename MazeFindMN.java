/**
 * @Name Michael Nguyen
 * @Course COSC 2436
 * @ProgSet 2 Problem 3
 * @Description Write a program to determine if a maze has an exit. The maze
 *              will have the
 *              entrance point at the top left corner and have an exit point
 *              anywhere in the far-right
 *              column. The 1s represent the path and 0s represent the walls of
 *              the maze. A valid path
 *              will be a continuous block of 1s that connects the top left
 *              corner to any spot on the
 *              far-right column and can only be connected horizontally or
 *              vertically- diagonal
 *              connections are not legal. Input each maze from the keyboard in
 *              the range [2,10].
 *              Error check size. If a valid path exists, output to the screen
 *              the matrix, the
 *              shortest path distance, and the path itself denoted by the
 *              letter P, otherwise output
 *              "Exit not found" if no path leading out of the maze is found.
 *              Finally, the program
 *              should ask if the user wants to run the program again (Check
 *              case). Use a Stack data
 *              structure. Refer to the sample output below.
 */

 import java.util.*;

public class MazeFindMN {

    /**
     * @name Node
     * @category Class
     * @description data structure that contains a value and a pointer to the next
     *              node
     */
    static class Node {
        // store the x and y direction where we can move
        static private int x;
        static private int y;

        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }// end


        /**
         * @name getX
         * @category Method
         * @return The value of the variable x.
         * @description Method returns the value of the variable x.
         */
        public int getX() {
            return x;
        }// end getX

        /**
         * @name setX
         * @category Method
         * @param x the coordinate of column to move
         * @description Method sets the horizontal move value to x.
         */
        public void setX(int x) {
            this.x = x;
        }// end setX

        /**
         * @name getY
         * @category Method
         * @return The value of the variable y.
         * @description Method returns the value of the variable y.
         */
        public int getY() {
            return y;
        }// end getY

        /**
         * @name setY
         * @category Method
         * @param y the coordinate of row to move
         * @description Method sets the vertical move value to y.
         */
        public void setY(int y) {
            this.y = y;
        }// end setY        
    }


    static LinkedList<Node> route = new LinkedList<Node>();
    public static void main(String[] args) {

        char answer = 'Y';
        while(answer == 'Y' || answer == 'y'){
            System.out.println("Please enter the rows in the matrix");
            Scanner sc = new Scanner(System.in);
            int row = sc.nextInt();
            System.out.println("Please enter the columns in the matrix");
            int column = sc.nextInt();
    
            int[][] maze = new int[row][column];
    
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    System.out.println(String.format("Enter first[%d][%d] integer", r, c));
                    maze[r][c] = sc.nextInt();
                }
            }
    
            System.out.println("Enter the maze:\n");
            print2dArray(maze);
    
            // Create new node with starting position of 0,0 and push onto the stack
            Node r = new Node(0,0);
            route.push(r);
            // count for number of tries in the routes
            int count = 0;
    
            while(true){
                count++;
                int y = route.peek().y;
                int x = route.peek().x;
        
                // mark the position that we visited with 0
                maze[y][x] = -1;
                
                if(isValid(y+1, x, maze)){
                    // moving down in the maze
                    if(maze[y+1][x] == 2) {
                        System.out.println("Exit found!");
                        return;
                    } else if(maze[y+1][x] == 1){
                        route.push(new Node(y+1, x));
                        continue;
                    }
                }
    
                if(isValid(y-1, x, maze)){
                    // moving up in the maze
                    if(maze[y-1][x] == 2) {
                        System.out.println("Exit found!");
                        return;
                    } else if(maze[y-1][x] == 1){
                        route.push(new Node(y-1, x));
                        continue;
                    }
                }
    
                if(isValid(y, x+1, maze)){
                    // moving right in the maze
                    if(maze[y][x+1] == 1 && !isValid(y, x+2, maze))
                     {
                        count++;
                        // Exit can only be found going to the right
                        maze[y][x+1] = -1;
                        System.out.println("\nResult:");
                        print2dArray(maze);
                        System.out.println("\nExit found in " + count + " steps");
                        System.out.print("\nRun Again (Y/N): ");
                        answer = sc.next().charAt(0);
                        break;
                    } else if(maze[y][x+1] == 1){
                        route.push(new Node(y, x+1));
                        continue;
                    }
                }
                
                route.pop();
                if(route.size() <= 0){
                    System.out.println("No Path Exists");
                    System.out.print("\nRun Again (Y/N): ");
                    answer = sc.next().charAt(0);   
                    break;
                }
            }

        } 
    }
    public static Boolean isValid(int y, int x, int[][] maze){
        if(y < 0 || y >= maze.length || x < 0 || x >= maze[y].length){
            return false;
        }
        return true;
    }
    private static void print2dArray(int[][] matrix) {
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == -1){
                    System.out.print("P\t");
                }
                else{
                    System.out.print(matrix[r][c] + "\t");
                }
			}
			System.out.println();
		}
	}
}