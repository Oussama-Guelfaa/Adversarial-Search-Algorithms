package fr.emse.ai.adversarial.ticTacToe;

import fr.emse.ai.adversarial.Game;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame implements Game<List<Integer>, Integer, Integer> {

    public final static Integer[] players = {2, 1};
    public final static List<Integer> initialState = new ArrayList<Integer>(List.of(1,0,0,0,0,0,0,0,0,0));



    @Override
    public List<Integer> getInitialState() {
        return initialState;
    }

    @Override
    public Integer[] getPlayers() {
        return players;
    }

    @Override
    public Integer getPlayer(List<Integer> state) {
        return state.get(0);
    }

    @Override
    public List<Integer> getActions(List<Integer> state) {
        ArrayList<Integer> actions = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            if(state.get(i)==0){
                actions.add(i);
            }
        }
        return actions;
    }

    @Override
    public List<Integer> getResult(List<Integer> state, Integer action) {
        Integer player;

        if(state.get(0)==players[0]){
            player = players[1];
        }
        else {
            player = players[0];
        }
        List<Integer> newState = new ArrayList<Integer>(state);
        newState.set(action,state.get(0));
        newState.set(0,player);

        return newState;
    }

    @Override
    public boolean isTerminal(List<Integer> state) {
        if (state.contains(0)){
            return  (won(state,List.of(1,2,3)) || //every possible solution
                    won(state,List.of(4,5,6)) ||
                    won(state,List.of(7,8,9)) ||
                    won(state,List.of(1,4,7)) ||
                    won(state,List.of(2,5,8)) ||
                    won(state,List.of(3,6,9)) ||
                    won(state,List.of(1,5,9)) ||
                    won(state,List.of(3,5,7)));
        }
        else { //all the positions are played, so it is finished
            return true;
        }
    }

    @Override
    public double getUtility(List<Integer> integers, Integer integer) {
        return 0;
    }

    private boolean won(List<Integer> state,List<Integer> finishCombination){
        Integer ra = state.get(finishCombination.get(0));
        Integer rb = state.get(finishCombination.get(1));
        Integer rc = state.get(finishCombination.get(2));

        if(ra==0 || rb ==0 || rc==0){ //the grid is empty
            return false;
        }

        return (ra == rb) && (rb==rc); //check that the same play have all of these positions
    }
}
