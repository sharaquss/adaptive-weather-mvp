package com.android.szparag.newadaptiveweather.dagger.components;

import com.android.szparag.newadaptiveweather.AppController;
import com.android.szparag.newadaptiveweather.MainActivityFragment;
import com.android.szparag.newadaptiveweather.activities.MainActivity;
import com.android.szparag.newadaptiveweather.dagger.modules.AdaptiveWeatherModule;
import com.android.szparag.newadaptiveweather.dagger.modules.NetworkingModule;
import com.android.szparag.newadaptiveweather.presenters.BasePresenter;
import com.android.szparag.newadaptiveweather.utils.Utils;
import com.android.szparag.newadaptiveweather.views.BaseView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ciemek on 21/09/2016.
 */

@Singleton
@Component(modules = { AdaptiveWeatherModule.class, NetworkingModule.class})
public interface MainComponent {

    void inject(MainActivity injectionTarget);
    void inject(MainActivityFragment injectionTarget);
    void inject(BaseView injectionTarget);
    void inject(AppController injectionTarget);
    void inject(Utils injectionTarget);

}