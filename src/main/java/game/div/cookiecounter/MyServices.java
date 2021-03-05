package game.div.cookiecounter;

public interface MyServices {

    void startSignInIntent();
    boolean isSignedIn();
    void showLeaderBoard();
    void submitScore(int score);

}
