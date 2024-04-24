package iialib.games.algs.algorithms;

import iialib.games.algs.GameAlgorithm;
import iialib.games.algs.IHeuristic;
import iialib.games.model.IBoard;
import iialib.games.model.IMove;
import iialib.games.model.IRole;

public class MiniMax<Move extends IMove,Role extends IRole,Board extends IBoard<Move,Role,Board>> implements GameAlgorithm<Move,Role,Board> {

	// Constants
	/** Defaut value for depth limit 
     */
	private final static int DEPTH_MAX_DEFAUT = 4;

	// Attributes
	/** Role of the max player 
     */
	private final Role playerMaxRole;

	/** Role of the min player 
     */
	private final Role playerMinRole;

	/** Algorithm max depth
     */
	private int depthMax = DEPTH_MAX_DEFAUT;

	
	/** Heuristic used by the max player 
     */
	private IHeuristic<Board, Role> h;

	//
	/** number of internal visited (developed) nodes (for stats)
     */
	private int nbNodes;
	
	/** number of leaves nodes nodes (for stats)

     */
	private int nbLeaves;

	// --------- Constructors ---------

	public MiniMax(Role playerMaxRole, Role playerMinRole, IHeuristic<Board, Role> h) {
		this.playerMaxRole = playerMaxRole;
		this.playerMinRole = playerMinRole;
		this.h = h;
	}

	//
	public MiniMax(Role playerMaxRole, Role playerMinRole, IHeuristic<Board, Role> h, int depthMax) {
		this(playerMaxRole, playerMinRole, h);
		this.depthMax = depthMax;
	}

	/*
	 * IAlgo METHODS =============
	 */

	@Override
	public Move bestMove(Board board, Role playerRole) {
		System.out.println("[MiniMax]");

		Move bestMove = null;
		int bestScore = (playerRole == playerMaxRole) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		for (Move move : board.possibleMoves(playerRole)) {
			Board newBoard = board.play(move, playerRole);
			int score = miniMax(newBoard, (playerRole == playerMaxRole) ? playerMinRole : playerMaxRole, depthMax - 1);

			if (playerRole == playerMaxRole && score > bestScore) {
				bestScore = score;
				bestMove = move;
			} else if (playerRole == playerMinRole && score < bestScore) {
				bestScore = score;
				bestMove = move;
			}
		}

		return bestMove;
	}

	/*
	 * PUBLIC METHODS ==============
	 */

	public String toString() {
		return "MiniMax(ProfMax=" + depthMax + ")";
	}

	/*
	 * PRIVATE METHODS ===============
	 */
	private int miniMax(Board board, Role playerRole, int depth) {
		if (depth == 0 || board.isGameOver()) {
			return h.eval(board, playerRole);
		}

		int bestScore = (playerRole == playerMaxRole) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		for (Move move : board.possibleMoves(playerRole)) {
			Board newBoard = board.play(move, playerRole);
			int score = miniMax(newBoard, (playerRole == playerMaxRole) ? playerMinRole : playerMaxRole, depth - 1);

			if (playerRole == playerMaxRole && score > bestScore) {
				bestScore = score;
			} else if (playerRole == playerMinRole && score < bestScore) {
				bestScore = score;
			}
		}

		return bestScore;
	}
}
