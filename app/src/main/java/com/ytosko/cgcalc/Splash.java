package com.ytosko.cgcalc;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splash extends AwesomeSplash {

    @Override
    public void initSplash ( ConfigSplash configSplash ) {

        ActionBar a = getSupportActionBar();
        a.hide();

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        configSplash.setBackgroundColor(R.color.x);
        configSplash.setAnimLogoSplashDuration( 3000 );
        configSplash.setRevealFlagX( Flags.REVEAL_LEFT );
        configSplash.setRevealFlagX( Flags.REVEAL_BOTTOM );
        configSplash.setLogoSplash( R.drawable.p5s );
        configSplash.setAnimLogoSplashDuration( 5000 );

        configSplash.setAnimLogoSplashTechnique( Techniques.BounceIn );
    }

    @Override
    public void animationsFinished () {
        startActivity( new Intent( Splash.this , MainActivity.class) );
        finish();

    }
}
