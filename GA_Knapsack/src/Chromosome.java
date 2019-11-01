import java.util.*;

public class Chromosome {
	public int[] genes;
	public double fitness;

	public Chromosome(int chromosomeLength) {
		this.genes = new int[chromosomeLength];
		this.fitness = 0;
	}



	public void generateGenes() {
		Random rand = new Random();
		for (int i = 0; i < genes.length; i++) {
			double value = rand.nextDouble();
			if (value <= 0.8) {
				genes[i] = 1;
			} 
			else
				genes[i] = 0;
		}
	}

	public void calcFitness(Vector<Item> items, int s) {
		int totalWeight = 0;
		int totalValue = 0;

		for (int i = 0; i < genes.length; i++) {
			totalWeight += items.get(i).weight * genes[i];
			totalValue += items.get(i).value * genes[i];
		}
		if (totalWeight > s) {
			fitness = 1 / totalValue; 
		} else {
			fitness = totalValue;
		}

	}
}
