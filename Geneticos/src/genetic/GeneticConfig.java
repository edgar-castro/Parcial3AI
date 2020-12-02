package genetic;

import java.util.Random;

public class GeneticConfig {
	
	public static final int N_ATRRIBUTES 		= 3;//5;
	public static final int N_CLASSES			= 13;//32;
	
	public static final int [] HIDDEN_RANGE 	= {1, 5};
	public static final int HIDDEN_DELTA 		= 1;
	public static final int HIDDEN_BITS 		= calculateBits(HIDDEN_RANGE, HIDDEN_DELTA);
	
	public static final int [] NEURONS_RANGE 	= {N_ATRRIBUTES, N_ATRRIBUTES + N_CLASSES};
	public static final int NEURONS_DELTA 		= 1;
	public static final int NEURONS_BITS 		= calculateBits(NEURONS_RANGE, NEURONS_DELTA);
	
	public static final int [] EPOCHS_RANGE 	= {100, 1000};
	public static final int EPOCHS_DELTA 		= 20;
	public static final int EPOCH_BITS 			= calculateBits(EPOCHS_RANGE, EPOCHS_DELTA);
	
	public static final float [] LEARNING_RANGE	= {0f, 0.3f};
	public static final int LEARNING_DELTA 		= 100;
	public static final int LEARNING_BITS 		= bitsForNumber(LEARNING_DELTA);
	
	public static final float [] MOMENTUM_RANGE	= {0f, 0.3f};
	public static final int MOMENTUM_DELTA 		= 20;
	public static final int MOMENTUM_BITS 		= bitsForNumber(MOMENTUM_DELTA);
	
	public static final int CHROMOSOME_SIZE 	= NEURONS_BITS + HIDDEN_BITS + EPOCH_BITS + LEARNING_BITS + MOMENTUM_BITS;
	
	public static final int [] BIT_PER_PARAM 	= {HIDDEN_BITS, NEURONS_BITS, EPOCH_BITS, LEARNING_BITS, MOMENTUM_BITS};		
	
	public static final int INITIAL_POPULATION 	= 30;
	public static final float SURVIVAL_FACTOR 	= 0.6f;
	public static final int CHILDS 				= 2; //Nunca se usa
	public static final float LIMIT				= 86f;
	public static final float MUTATION			= 3f;
	public static final int MAX_GEN 			= 3;
	
	public static final int SELECT_A_PERCENT	= 40;
	public static final int SELECT_B_PERCENT	= 40;
	public static final int SELECT_C_PERCENT	= 40;
	public static final int SELECT_D_PERCENT	= 40;
	
	public static void print() {
		System.out.println("Configuracion de cromosoma:");
		System.out.println("[HIDDEN]>	Rango:[" + HIDDEN_RANGE[0]		+ ", " + HIDDEN_RANGE[1] + 		"] | Delta: " + HIDDEN_DELTA + 		" | Numero de bits usados: " + HIDDEN_BITS);
		System.out.println("[NEURALS]>	Rango:[" + NEURONS_RANGE[0] 	+ ", " + NEURONS_RANGE[1] + 	"] | Delta: " + NEURONS_DELTA + 	" | Numero de bits usados: " + NEURONS_BITS);
		System.out.println("[EPOCHS]> 	Rango:[" + EPOCHS_RANGE[0] 		+ ", " + EPOCHS_RANGE[1] + 		"] | Delta: " + EPOCHS_DELTA + 		" | Numero de bits usados: " + EPOCH_BITS);
		System.out.println("[LEARNING]> Rango:[" + LEARNING_RANGE[0] 	+ ", " + LEARNING_RANGE[1] +	"] | Delta: " + LEARNING_DELTA + 	" | Numero de bits usados: " + LEARNING_BITS);
		System.out.println("[MOMENTUM]> Rango:[" + MOMENTUM_RANGE[0] 	+ ", " + MOMENTUM_RANGE[1] +	"] | Delta: " + MOMENTUM_DELTA + 	" | Numero de bits usados: " + MOMENTUM_BITS);
	}
	
	public static boolean inRange(int [] range, int number) {
		return number >= range[0] && number <= range[1];
	}
	
	public static boolean inRange(float [] range, float number) {
		return number >= range[0] && number <= range[1];
	}
	
	private static int calculateBits(int [] range, int delta) {
		int max = range[1] - range[0];
		int total = max / delta;
		return bitsForNumber(total);
	}
	
	private static int bitsForNumber(int number) {
		int n_bits = 0;
		while(number > 0) {
			n_bits++;
			number >>= 1;
		}
		return n_bits;
	}
	
	public static char [] randomChromosome() {
		Random rnd = new Random();
		int h = rnd.nextInt(HIDDEN_RANGE[1]  - HIDDEN_RANGE[0]);
		int n = rnd.nextInt(NEURONS_RANGE[1] - NEURONS_RANGE[0]);
		int e = rnd.nextInt((EPOCHS_RANGE[1] - EPOCHS_RANGE[0]) / EPOCHS_DELTA);
		int l = rnd.nextInt(LEARNING_DELTA);
		int m = rnd.nextInt(MOMENTUM_DELTA);
		String chromo = toBinary(h, HIDDEN_BITS) +
						toBinary(n, NEURONS_BITS) +
						toBinary(e, EPOCH_BITS) +
						toBinary(l, LEARNING_BITS) +
						toBinary(m, MOMENTUM_BITS);
		return chromo.toCharArray();
	}
	public static String toBinary(int number, int bits) {
		StringBuffer binary = new StringBuffer();
		String c = Integer.toBinaryString(number);
		if(c.length() != bits)
			for(int i = 0; i < bits - c.length(); i++)
				binary.append("0");
		binary.append(c);
		return binary.toString();
	}
	
}
