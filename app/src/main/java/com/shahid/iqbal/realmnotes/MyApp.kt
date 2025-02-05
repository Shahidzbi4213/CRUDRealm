package com.shahid.iqbal.realmnotes

import android.app.Application
import com.shahid.iqbal.realmnotes.model.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

/**
 * Created by shahid-iqbal on 2/4/25
 */
class MyApp : Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()

        setRealmSdk()
    }

    private fun setRealmSdk() {
        val configuration = RealmConfiguration.Builder(schema = setOf(Note::class),)
            .compactOnLaunch()
            .build()

        realm = Realm.open(configuration)
    }
}