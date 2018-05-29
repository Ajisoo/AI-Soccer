# AI-Soccer
A more extensive example of neural networks combined with genetic algorithms. This time with a more complicated game, pong/soccer combination.

Similar to Pong Learning, this runs a similar network and learning algorithm. However, the main difference this time is the much more complicated game to learn.

While Pong Learning had 4 weights, 2 meaningful inputs, and 1 output, AI Soccer has 771 Weights, 16 meaningful inputs, and 3 outputs.

Running the program is very similar to Pong Learning. However, in this case, it is important to note that the fitness function (definied in PlayGamesThread) can and should be changed. In addition, this network primarily learns by playing against itself (other networks in the same generation), instead of one specific AI.

In Pong Learning, this was fine, as the AI could not really be "exploited" in ways that were unnatural. However, this is not true for the AI in AI Soccer. By playing against each other, the networks learn by playing against continually more difficult opponents and improving against them.

Instructions:
Run Main.main() in order to have the weights "learn".
Creating weights can be turned on or off by commenting out the first line of code.
The fitness function can be defined in PlayGamesThread. However, not all variables that might be needed exit, so changes to GameState will also have to be made.
Similar to Pong Learning, ViewExampleGame shows a game where one can specify which weights to play against each other, or against the standard AI.


At the time of writing this, I have the program learning in the background. So far it looks good, but I want to see how good they can get :).
