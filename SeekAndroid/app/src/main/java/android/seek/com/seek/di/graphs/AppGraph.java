package android.seek.com.seek.di.graphs;

import android.seek.com.seek.arch.SeekApplication;
import android.seek.com.seek.ui.MainActivity;

/**
 * Created by vermas12 on 26/3/19.
 */

public interface AppGraph {

    void inject(SeekApplication app);

    void inject(MainActivity activity);

}
