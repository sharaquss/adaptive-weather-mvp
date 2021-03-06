package com.android.szparag.newadaptiveweather.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.szparag.newadaptiveweather.AdaptiveWeatherApplication;
import com.android.szparag.newadaptiveweather.backend.RealmUtils;
import com.android.szparag.newadaptiveweather.backend.interceptors.AvoidNullsInterceptor;
import com.android.szparag.newadaptiveweather.backend.services.WeatherService;
import com.android.szparag.newadaptiveweather.presenters.BulkWeatherInfoBasePresenter;
import com.android.szparag.newadaptiveweather.presenters.BulkWeatherInfoPresenter;
import com.android.szparag.newadaptiveweather.presenters.OneDayWeatherInfoBasePresenter;
import com.android.szparag.newadaptiveweather.presenters.OneDayWeatherInfoPresenter;
import com.android.szparag.newadaptiveweather.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by ciemek on 21/09/2016.
 */

@Module
public class AdaptiveWeatherModule {

    private Application application;

    //The Application class is single dependency that needs to be satisfied 'manually'
    public AdaptiveWeatherModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public AdaptiveWeatherApplication provideAppController() throws ClassCastException {
        return (AdaptiveWeatherApplication)application;
    }

    @Provides
    @Singleton
    public AvoidNullsInterceptor provideAvoidNullsInterceptor() {
        return new AvoidNullsInterceptor();
    }

    @Provides
    public Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public RealmUtils provideRealmUtils(Realm realm) {
        return new RealmUtils(realm);
    }

    @Provides
    @Singleton
    public BulkWeatherInfoBasePresenter provideBulkWeatherInfoPresenter(WeatherService service,
                                                                        @Named(Constants.GOOGLEMAPSSTATIC_BASEURL) String baseUrl,
                                                                        @Named(Constants.GOOGLEMAPSSTATIC_APIKEY) String apiKey) {
        return new BulkWeatherInfoPresenter(service, baseUrl, apiKey);
    }

    @Provides
    @Singleton
    public OneDayWeatherInfoBasePresenter provideOneDayWeatherInfoPresenter(WeatherService service) {
        return new OneDayWeatherInfoPresenter(service);
    }


}
