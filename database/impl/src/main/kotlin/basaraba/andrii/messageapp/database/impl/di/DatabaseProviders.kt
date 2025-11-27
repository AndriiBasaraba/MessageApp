package basaraba.andrii.messageapp.database.impl.di

import android.content.Context
import basaraba.andrii.messageapp.database.impl.room.MessageDao
import basaraba.andrii.messageapp.database.impl.room.MessagesDb


internal fun provideAppDatabase(appContext: Context): MessagesDb =
    MessagesDb.getInstance(appContext)

internal fun provideMessageDao(database: MessagesDb): MessageDao = database.messageDao()
