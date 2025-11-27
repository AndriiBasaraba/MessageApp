package basaraba.andrii.messageapp.database.impl.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [MessageEntity::class],
    version = 1,
    exportSchema = true
)
internal abstract class MessagesDb : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object Companion {
        private fun getDbBuilder(
            context: Context,
            dbName: String = "Messages.db"
        ): Builder<MessagesDb> =
            databaseBuilder(context.applicationContext, MessagesDb::class.java, dbName)
//                .fallbackToDestructiveMigration(true)
//                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)

        fun getInstance(context: Context): MessagesDb = getDbBuilder(context).build()

        private fun getInMemoryDbBuilder(context: Context): Builder<MessagesDb> =
            Room.inMemoryDatabaseBuilder(context, MessagesDb::class.java)

        fun getInMemoryDb(context: Context): MessagesDb = getInMemoryDbBuilder(context).build()
    }
}
