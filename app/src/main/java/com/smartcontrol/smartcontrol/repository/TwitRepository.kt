package com.smartcontrol.smartcontrol.repository

import com.smartcontrol.smartcontrol.db.TwitDao
import com.smartcontrol.smartcontrol.model.Twit
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class TwitRepository @Inject constructor(private val twitDao: TwitDao) {

    fun getMessages(): Flowable<List<Twit>> {
        return twitDao.getAll()
    }

    fun insertTwit(twit: Twit): Completable {
        return Completable.fromAction { twitDao.insert(twit) }
    }



}