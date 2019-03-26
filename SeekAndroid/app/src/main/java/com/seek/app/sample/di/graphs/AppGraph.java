package com.seek.app.sample.di.graphs;

import com.seek.app.sample.arch.SeekApplication;
import com.seek.app.sample.ui.MainActivity;

/**
 * Created by vermas12 on 26/3/19.
 */

public interface AppGraph {

    void inject(SeekApplication app);

    void inject(MainActivity activity);

}
