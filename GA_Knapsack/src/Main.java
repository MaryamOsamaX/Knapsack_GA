import java.io.*;
import java.util.*;


public class Main {
	

	public static void main(String[] args) {
		BufferedReader reader;
		Vector<Item> items=new Vector<Item>();
		try {
			reader = new BufferedReader(new FileReader("src/input_example.txt"));
			int C = Integer.parseInt(reader.readLine());///num of test cases
			
			reader.readLine();
			
			for(int i=0 ; i<C; i++)
			{
				int N= Integer.parseInt(reader.readLine()); ///num of items
				int S= Integer.parseInt(reader.readLine());///knapsack size
				for(int j=0; j<N;j++)
				{
					String line=reader.readLine();
					String []arr=line.split(" ");
					 
				items.add(new Item( Integer.parseInt(arr[0]),Integer.parseInt(arr[1])));
				KnapsackGA ks=new KnapsackGA(N,N,S,items);
				//call rest of functions 
				
				}
				if(i!=C-1)
				{
					reader.readLine();
					reader.readLine();
				}
				
				
			}
			
		
			
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		

	}

}
