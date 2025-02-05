package com.shahid.iqbal.realmnotes.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/**
 * Created by shahid-iqbal on 2/4/25
 */
class Note : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var title: String = ""
    var description: String = ""
}