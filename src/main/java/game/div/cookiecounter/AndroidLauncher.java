package game.div.cookiecounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.android.friendsmash.FriendSmashApplication;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.LayoutGameActivity;

import java.util.concurrent.Executor;

import game.div.monymony.R;

public class AndroidLauncher extends Activity implements MyServices {

    private GoogleSignInClient mGoogleSignInClient;
    private LeaderboardsClient mLeaderboardsClient;
    private PlayersClient mPlayersClient;

    private static final String TAG=AndroidLauncher.class.getSimpleName();

    private static final int RC_SIGN_IN = 9001;
    private static final int RC_UNUSED = 5001;
    private static final int RC_LEADERBOARD_UI = 9004;

    private String greetingMsg="Welcome, ";
    private boolean greetingDisplayed;

    private static AndroidLauncher _instance = null;

    public static AndroidLauncher getInstance() {

        return _instance;

    }


    @Override
    public boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    private void signInSilently() {

        mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            greetingMsg="Welcome back, ";
                            onConnected(task.getResult());
                        } else {
                            onDisconnected();
                        }
                    }
                });
    }

    private void onConnected(GoogleSignInAccount googleSignInAccount) {

        mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
        mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);

        mPlayersClient.getCurrentPlayer()
                .addOnCompleteListener(new OnCompleteListener<Player>() {
                    @Override
                    public void onComplete(@NonNull Task<Player> task) {
                        String displayName;
                        if (task.isSuccessful()) {
                            displayName = task.getResult().getDisplayName();
                        } else {
                            Exception e = task.getException();
                            //handleException(e, getString(R.string.players_exception));
                            displayName = "???";
                        }

                        if(!greetingDisplayed)
                            welcomeMessage(displayName);
                    }
                });
    }

    private void welcomeMessage(String name){

        Toast toast = Toast.makeText(this, greetingMsg + name, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        View view = toast.getView();
        TextView text = (TextView) view.findViewById(android.R.id.message);
        toast.show();
        greetingDisplayed=true;
    }

    @Override
    public void startSignInIntent() {
        GameActivity.getInstance().startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                greetingMsg="Welcome, ";
                onConnected(account);
            } catch (ApiException apiException) {
                String message = apiException.getMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }

                onDisconnected();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        signInSilently();
    }

    private void signOut() {

        if (!isSignedIn()) {
            Log.w(TAG, "signOut() called, but was not signed in!");
            return;
        }

        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        boolean successful = task.isSuccessful();
                        Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));

                        onDisconnected();
                    }
                });
    }

    private void onDisconnected() {

        mLeaderboardsClient = null;
        mPlayersClient = null;
    }


    @Override
    public void submitScore(int score){
        if(isSignedIn()) {
            mLeaderboardsClient.submitScore(getString(R.string.leaderboard_id), score);
        } else {
            GameActivity.getInstance().startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);

        }
    }

    @Override
    public void showLeaderBoard() {
        if(isSignedIn()) {
            mLeaderboardsClient.getLeaderboardIntent(getString(R.string.leaderboard_id))
                    .addOnSuccessListener(new OnSuccessListener<Intent>() {
                        @Override
                        public void onSuccess(Intent intent) {
                            startActivityForResult(intent, RC_LEADERBOARD_UI);
                        }
                    });
        }
    }

    private void handleException(Exception e, String details) {
        int status = 0;

        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            status = apiException.getStatusCode();
        }

        String message = details + " : " + status + " --> " + e.getCause();

        new AlertDialog.Builder(this)
                .setMessage(message)
                .setNeutralButton(android.R.string.ok, null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _instance = this;

        greetingDisplayed = false;

    }

}
