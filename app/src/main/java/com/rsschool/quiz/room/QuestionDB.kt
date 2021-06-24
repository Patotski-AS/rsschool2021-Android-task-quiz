package com.rsschool.quiz.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rsschool.quiz.getStartQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Question::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class QuestionDB : RoomDatabase() {

    abstract fun questionDAO(): QuestionDAO

    companion object {
        @Volatile
        private var INSTANCE: QuestionDB? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): QuestionDB {
            Log.i("MyLog", "getInstance")
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionDB::class.java,
                        "question"
                    )
                        .addCallback(QuestionDBCallback(scope))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private class QuestionDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var questionDAO = database.questionDAO()
                    questionDAO.clear()
                    val questions = getStartQuestion()
                    questions.forEach { questionDAO.insert(it) }
                }
            }
        }
    }
}