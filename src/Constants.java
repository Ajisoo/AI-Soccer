
/*
 * This file is a list of constants related to the neural network,
 * whether they are variables or the takeAction method.
 */

public class Constants {

	public static final int THREADS = 10;

	public static final int INPUTS = 16; // see takeAction for the list of
											// parameters (note that bias is
											// treated separately)
	public static final int HIDDEN_LAYERS = 3;
	public static final int NODES_PER_LAYER = 16;
	public static final int OUTPUT = 3; // x change, y change, whether to "hit"
										// or not.
	public static final double CROSSOVER_RATE = 0.1;
	public static final double MUTATION_RATE = 0.1;

	// calculation on weights (Enemy 1x, 2x, 3x all have same weights. Also true for allies)
	public static final int WEIGHTS = HIDDEN_LAYERS * NODES_PER_LAYER + OUTPUT
			+ NODES_PER_LAYER * NODES_PER_LAYER * (HIDDEN_LAYERS - 1) + NODES_PER_LAYER * (INPUTS + OUTPUT)
			- (2 + 4) * NODES_PER_LAYER;
	
	public static final int POPULATION_SIZE = 50;

	public static Action takeAction(double[] weights, double[] inputs) {
		int count = 0;

		// fill up all the nodes in the hidden layers.
		double[][] nodes = new double[Constants.HIDDEN_LAYERS][Constants.NODES_PER_LAYER];
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {

				int inputsPerNode = Constants.NODES_PER_LAYER;

				if (i == 0)
					inputsPerNode = Constants.INPUTS;

				// BIAS
				nodes[i][j] += weights[count];
				count++;

				for (int k = 0; k < inputsPerNode; k++) {
					if (i == 0) {
						// Treat all allies as the same, and all
						// enemies as the same
						if (k == 8 || k == 12 || k == 14) {
							count--;
							count--;
						}
						nodes[i][j] += weights[count] * inputs[k];
					} else {
						nodes[i][j] += weights[count] * nodes[i - 1][k];
					}
					count++;
				}
				nodes[i][j] = 2 / (1 + Math.pow(Math.E, -nodes[i][j])) - 1;
			}
		}

		// BIAS for end
		double endx = weights[count];
		count++;
		double endy = weights[count];
		count++;
		double endHit = weights[count];
		count++;

		// Find the final outputs.
		for (int i = 0; i < Constants.NODES_PER_LAYER; i++) {
			endx += nodes[nodes.length - 1][i] * weights[count];
			count++;
		}

		for (int i = 0; i < Constants.NODES_PER_LAYER; i++) {
			endy += nodes[nodes.length - 1][i] * weights[count];
			count++;
		}

		for (int i = 0; i < Constants.NODES_PER_LAYER; i++) {
			endHit += nodes[nodes.length - 1][i] * weights[count];
			count++;
		}

		endx = 5 * Math.min(1, Math.max(-1, endx));
		endy = 5 * Math.min(1, Math.max(-1, endy));
		return new Action(endx, endy, endHit > 0);
	}
}
