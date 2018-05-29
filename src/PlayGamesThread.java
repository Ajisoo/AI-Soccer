
/*
 * This is the thread that plays the games and records the scores.
 */

public class PlayGamesThread extends Thread {
		
		int index;
		double[] scores;
		
		public PlayGamesThread(int index, double[] scores){
			this.index = index;
			this.scores = scores;
			start();
		}
		
		// Choose which "run" to play.
		// The top has the teams play against each other.
		// The bottom has them play against AndrewMethod.
		
		public void run(){ // play against each other
			for (int i = index * Constants.POPULATION_SIZE / Constants.THREADS / 2; i < (index + 1) * Constants.POPULATION_SIZE / Constants.THREADS / 2; i++){
				GameState gameState = new GameState(2*i, 2*i+1);
				for (int j = 0; j < 10000; j++){
					gameState.update();
				}
				
				// Fitness function below.
				
				scores[2*i] = 2 * gameState.lScore + 0.01 - gameState.rScore;// (double)gameState.leftBallTouch / 50 - gameState.rScore;
				scores[2*i+1] = 2 * gameState.rScore + 0.01 - gameState.lScore; //(double)gameState.rightBallTouch / 50 - gameState.lScore;
			}
		}
		
//		public void run(){ // play against ai
//			for (int i = index * Constants.POPULATION_SIZE / Constants.THREADS; i < (index + 1) * Constants.POPULATION_SIZE / Constants.THREADS; i++){
//				GameState gameState = new GameState(i, -1);
//				for (int j = 0; j < 10000; j++){
//					gameState.update();
//				}
//				
//				scores[i] = gameState.lScore - gameState.rScore * 2 + 0.01;
//			}
//		}
	}