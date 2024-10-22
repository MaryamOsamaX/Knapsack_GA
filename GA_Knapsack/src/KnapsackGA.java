import java.io.IOException;
import java.util.*;

public class KnapsackGA {
	Vector<Chromosome> pop;
	Vector<Item> items;
	int popSize, n, s;
	double bestV;
	Chromosome sol;

	KnapsackGA(int popS, int n, int s, Vector<Item> itemp) {
		
		sol = new Chromosome(n);
		for(int i=0;i<n;i++) { sol.genes[i]=0; }
		bestV=0;
		/*sol.generateGenes();
		sol.calcFitness(itemp, s);
		bestV=sol.fitness;*/
		pop = new Vector<Chromosome>();
		items = new Vector<Item>();
		items = itemp;
		popSize = popS;
		this.n = n;
		this.s = s;

		for (int i = 0; i < popS; i++) {
			pop.add(new Chromosome(n));
			pop.get(i).generateGenes();
		}
	}

	public Vector<Integer> selection() {

		Vector<Integer> selected = new Vector<Integer>();
		Vector<Integer> rouletteWheel = new Vector<Integer>();
		rouletteWheel.add(0);
		int totalFitness = 0;
		/// initialize the roulette wheel
		for (int i = 0; i < pop.size(); i++) {
			totalFitness += pop.get(i).fitness;
			rouletteWheel.add(totalFitness);
		}

		Random random = new Random();
		for (int x = 0; x < pop.size() / 2; x++) {
			if (totalFitness != 0) {
				int random1 = random.nextInt(totalFitness), random2 = random.nextInt(totalFitness);
				Vector<Integer> s = new Vector<Integer>();

				for (int i = 0; i < rouletteWheel.size() - 1; i++) {
					if (s.size() == 2)
						break;
					if (random1 >= rouletteWheel.get(i) && random1 < rouletteWheel.get(i + 1)) {
						s.add(i);
					}
					if (random2 >= rouletteWheel.get(i) && random2 < rouletteWheel.get(i + 1)) {
						s.add(i);
					}
				}

				selected.add(s.get(0));
				selected.add(s.get(1));
			}

		}
		return selected;

	}

	public Vector<Integer> crossOver(Vector<Integer> selected) {

		Random random = new Random();
		for (int k = 0; k < selected.size() - 1; k += 2) {
			double r = random.nextDouble();
			if (r <= 0.7) {
				int begin = random.nextInt(n - 1);
				for (int i = begin; i < n; i++) {
					int temp = pop.get(selected.get(k)).genes[i];
					pop.get(selected.get(k)).genes[i] = pop.get(selected.get(k + 1)).genes[i];
					pop.get(selected.get(k + 1)).genes[i] = temp;
				}

			}
		}
		return selected;
	}

	public void mutation(Vector<Integer> crossed) {
		Random random = new Random();
		for (int i = 0; i < crossed.size(); i++) {
			for (int x = 0; x < pop.get(crossed.get(i)).genes.length; x++) {
				double r = random.nextDouble();
				if (r <= 0.01) {
					if (pop.get(crossed.get(i)).genes[x] == 1)
						pop.get(crossed.get(i)).genes[x] = 0;
					else
						pop.get(crossed.get(i)).genes[x] = 1;
				}
			}
		}
	}

	public void calcPopFitness() {
		for (int i = 0; i < pop.size(); i++) {
			pop.get(i).calcFitness(items, s);
		}
	}

	public void updateSolution() {
		for (int i = 0; i < pop.size(); i++) {
			if (bestV < pop.get(i).fitness) {
				bestV = pop.get(i).fitness;
				for(int j=0;j<sol.genes.length;j++) {
					sol.genes[j] = pop.get(i).genes[j];
				}
				
				//System.out.println("best value = "+bestV+" -> "+Arrays.toString(sol.genes));
			}
		}
	}

}
