package org.deathsbreedgames.gnp2.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import org.deathsbreedgames.gnp2.Game;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(500, 500);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Game();
        }
}