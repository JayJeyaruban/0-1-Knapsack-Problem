/**
 * Created by Jay on 17/08/2016.
 */
public class Knapsack {
	private int N;
	private int maxWeight;
	private int values[];
	private int weights[];
	private int table[][];

	public Knapsack(int maxWeight, int values[], int weights[]) {
		this.maxWeight = maxWeight;
		this.values = values;
		this.weights = weights;
		N = weights.length;

		table = new int[N][maxWeight + 1];
		// to make sure...
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= maxWeight; j++)
				table[i][j] = 0;
		}
	}

	private void sortItems() {
		for (int j = 1; j < N; j++) {
			for (int i = 1; i < N; i++) {
				if (weights[i] < weights[i - 1]) {
					int tmpWeight = weights[i];
					weights[i] = weights[i - 1];
					weights[i - 1] = tmpWeight;

					int tmpValue = values[i];
					values[i] = values[i - 1];
					values[i - 1] = tmpValue;
				}
			}
		}
	}

	public void knapsack() {
		sortItems();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= maxWeight; j++) {
				int above = 0;
				int tangent = 0;
				if (i > 0) {
					above = table[i - 1][j];
				}
				if (weights[i] <= j) {
					tangent = values[i];
					if (i > 0)
						tangent += table[i - 1][j - weights[i]];
				}
//				if (weights[i] <= j) {
//					tangent = values[i];
//					if (i > 0) {
//						above = table[i - 1][j];
//						tangent += table[i - 1][j - weights[i]];
//					}
//				}
				table[i][j] = max(above, tangent);
			}
		}
		System.out.println("Max value = " + table[N - 1][maxWeight]);
	}

	private int max(int x, int y) {
		if (x > y)
			return x;
		else
			return y;
	}

	public static void main(String[] args) {
		int values[] = {7, 3, 8, 6, 5};
		int weights[] = {10, 15, 2, 4, 5};

		new Knapsack(20, values, weights).knapsack();
	}

}
