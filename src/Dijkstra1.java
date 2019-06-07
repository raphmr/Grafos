import java.util.Scanner; //Scanner Function to take in the Input Values

public class Dijkstra1
{
    static Scanner scan; // scan is a Scanner Object

    public static void main(String[] args)
    {
        int[] preD = new int[6];
        int min = 999, nextNode = 0; // min holds the minimum value, nextNode holds the value for the next node.
        scan = new Scanner(System.in);
        int[] distance = new int[6]; // the distance matrix
        int[][] matrix = new int[][] {
                { 0, 2, 0, 0, 0, 7 },
                { 2, 0, 9, 0, 3, 4 },
                { 0, 9, 0, 5, 4, 3 },
                { 0, 0, 5, 0, 3, 0 },
                { 0, 3, 4, 3, 0, 1 },
                { 7, 4, 3, 0, 1, 0 }
        }; // the actual matrix
        int[] visited = new int[6]; // the visited array

        System.out.println("Enter the cost matrix");

        for (int i = 0; i < distance.length; i++)
        {
            visited[i] = 0; //initialize visited array to zeros
            preD[i] = 0;

            for (int j = 0; j < distance.length; j++)
            {
                if (matrix[i][j]==0)
                    matrix[i][j] = 999; // make the zeros as 999
            }
        }

        distance = matrix[0]; //initialize the distance array
        visited[0] = 1; //set the source node as visited
        distance[0] = 0; //set the distance from source to source to zero which is the starting point

        for (int counter = 0; counter < 6; counter++)
        {
            min = 999;
            for (int i = 0; i < 6; i++)
            {
                if (min > distance[i] && visited[i]!=1)
                {
                    min = distance[i];
                    nextNode = i;
                }
            }

            visited[nextNode] = 1;
            for (int i = 0; i < 6; i++)
            {
                if (visited[i]!=1)
                {
                    if (min+matrix[nextNode][i] < distance[i])
                    {
                        distance[i] = min+matrix[nextNode][i];
                        preD[i] = nextNode;
                    }

                }

            }

        }

        for(int i = 0; i < 6; i++)
            System.out.print("|" + distance[i]);

        System.out.println("|");

        int j;
        for (int i = 0; i <6; i++)
        {
            if (i!=0)
            {

                System.out.print("Path = " + (i + 1));
                j = i;
                do
                {
                    j = preD[j];
                    System.out.print(" <- " + (j + 1));
                }
                while(j != 0);
            }
            System.out.println();
        }
    }
}
