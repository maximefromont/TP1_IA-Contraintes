package games.dominos;

import iialib.games.algs.IHeuristic;

public class DominosHeuristics {
	
	public static IHeuristic<DominosBoard,DominosRole>  hVertical = (board,role) -> {

		if(role == DominosRole.VERTICAL) { //What role is it currenty ?
			return board.nbHorizontalMoves() - board.nbVerticalMoves();
		}
		else {
			return board.nbVerticalMoves() - board.nbHorizontalMoves();
		}
    };
    

	public static IHeuristic<DominosBoard,DominosRole> hHorizontal = (board,role) -> {
		if(role == DominosRole.HORIZONTAL) { //What role is it currenty ?
			return board.nbHorizontalMoves() - board.nbVerticalMoves();
		}
		else {
			return board.nbVerticalMoves() - board.nbHorizontalMoves();
		}
	};
   
}
	