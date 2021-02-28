package game.div.cookiecounter;

import android.content.Context;

import androidx.multidex.BuildConfig;

import com.google.android.gms.ads.AdListener;

import org.andengine.entity.sprite.UniformColorSprite;

public class ToastAdListener extends AdListener {
    private Context mContext;

    public ToastAdListener(Context context) {
        this.mContext = context;
    }

    public void onAdLoaded() {
    }

    public void onAdFailedToLoad(int errorCode) {
        String errorReason = BuildConfig.FLAVOR;
        switch (errorCode) {
            case UniformColorSprite.VERTEX_INDEX_X /*0*/:
                errorReason = "Internal error";
            case UniformColorSprite.VERTEX_INDEX_Y /*1*/:
                errorReason = "Invalid request";
            case UniformColorSprite.TEXTURECOORDINATES_INDEX_U /*2*/:
                errorReason = "Network Error";
            case UniformColorSprite.TEXTURECOORDINATES_INDEX_V /*3*/:
                errorReason = "No fill";
            default:
        }
    }

    public void onAdOpened() {
    }

    public void onAdClosed() {
    }

    public void onAdLeftApplication() {
    }
}
