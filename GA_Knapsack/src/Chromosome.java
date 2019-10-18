import java.util.*;

public class Chromosome {
	int[] genes;
	int fitness;

	public Chromosome(int chromosomeLength) {
		this.genes = new int[chromosomeLength];
		this.fitness = 0;
	}

	public void generateGenes() {
		Random rand = new Random();
		for (int i = 0; i < genes.length; i++) {
			double value = rand.nextDouble();
			if (value <= 0.5) {
				genes[i] = 1;
			} 
			else
				genes[i] = 0;
		}
	}

	public void calcFitness(Item[] items, int s) {
		int totalWeight = 0;
		int totalValue = 0;

		for (int i = 0; i < genes.length; i++) {
			totalWeight += items[i].weight * genes[i];
			totalValue += items[i].value * genes[i];
		}
		if (totalWeight > s) {
			fitness = 1; // why not 0
		} else {
			fitness = totalValue;
		}

	}
}
