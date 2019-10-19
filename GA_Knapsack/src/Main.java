import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		BufferedReader reader;
		int max_generation=50;
		try {
			reader = new BufferedReader(new FileReader("src/input_example.txt"));
			int C = Integer.parseInt(reader.readLine());///num of test cases
			//System.out.println("c= "+C);
			reader.readLine();
			
			for(int i=0 ; i<C; i++)
			{
				Vector<Item> items=new Vector<Item>();
				int N= Integer.parseInt(reader.readLine()); ///num of items
				//System.out.println("N= "+N);
				int S= Integer.parseInt(reader.readLine());///knapsack size
				//System.out.println("S= "+S);
				for(int j=0; j<N;j++)
				{
					String line=reader.readLine();
					String []arr=line.split(" ");
					 
				items.add(new Item( Integer.parseInt(arr[0]),Integer.parseInt(arr[1])));
				//System.out.println("Item"+ j +"= "+Integer.parseInt(arr[0])+" "+Integer.parseInt(arr[1]));
				}
				KnapsackGA ks=new KnapsackGA(200,N,S,items);
				//call rest of functions 
				for(int g=0;g<max_generation;g++){
		            ks.calcPopFitness();
		            ks.updateSolution();
		            Vector<Integer>selected=ks.selection();
		            Vector<Integer>crossed=ks.crossOver(selected);
		            ks.mutation(crossed);


		        }

				ks.updateSolution();
				
				System.out.println("Case "+(i+1) +" : "+ks.bestV);
				for(int x=0;x<N;x++) {
					if(ks.sol.genes[x] == 1) {
						System.out.println(items.get(x).weight+" "+items.get(x).value);
					}
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
