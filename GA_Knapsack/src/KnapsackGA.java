import java.util.*;


public class KnapsackGA {
		Vector<Chromosome> pop;
		Vector<Item> items;
		int popSize,n,s;
		
		KnapsackGA(int popS, int n, int s,Vector<Item> itemp ){
			pop=new Vector<Chromosome>();
			items=new Vector<Item>();
			items=itemp;
			popSize=popS;
			this.n=n;
			this.s=s;
			for(int i=0;i<popS;i++){
	            pop.add(new Chromosome(n));
	            pop.get(i).generateGenes();
	        }
		}
		
		public Vector<Integer> selection(){

			Vector<Integer>selected=new Vector<Integer>();
	        Vector<Integer> rouletteWheel = new Vector<Integer>();
	        rouletteWheel.add(0);
	        int totalFitness=0;
	        for(int i=0;i<pop.size();i++){
	            totalFitness+=pop.get(i).fitness;
	            rouletteWheel.add(totalFitness);
	        }
	        
	        Random random=new Random();

	        int random1=random.nextInt(totalFitness),random2=random.nextInt(totalFitness);
	        
	        for(int i=0;i<rouletteWheel.size()-1;i++){
	            if(selected.size()==2)
	                break;
	            if(random1>=rouletteWheel.get(i)&&random1<rouletteWheel.get(i+1)){
	                selected.add(i);
	            }
	            if(random2>=rouletteWheel.get(i)&&random2<rouletteWheel.get(i+1)){
	                selected.add(i);
	            }
	        }
	        return selected;

	    }
		
}
