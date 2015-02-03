package com.example.etudiant.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class MyDataBase extends SQLiteOpenHelper{

    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_INTITULE = "intitule";
    public static final String COLUMN_ANSWER_A = "answer_a";
    public static final String COLUMN_ANSWER_B = "answer_b";
    public static final String COLUMN_ANSWER_C = "answer_c";
    public static final String COLUMN_ANSWER_D = "answer_d";
    public static final String COLUMN_TRUTH = "truth";

    private static final String DATABASE_NAME = "questions.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_QUESTIONS
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_INTITULE + " text not null,"
            + COLUMN_ANSWER_A + " text not null,"
            + COLUMN_ANSWER_B + " text not null,"
            + COLUMN_ANSWER_C + " text not null,"
            + COLUMN_ANSWER_D + " text not null,"
            + COLUMN_TRUTH + " integer not null)";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //database.execSQL("DROP TABLE " + TABLE_QUESTIONS);
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MyDataBase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    public List<Question> showAllQuestions() {
        List<Question> questions = new LinkedList<Question>();
        String query = "SELECT * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Question question = null;
        if (cursor.moveToFirst()) {
            do {
                question = new Question();
                question.setId(Integer.parseInt(cursor.getString(0)));
                question.setIntitule(cursor.getString(1));
                question.setAnswer_a(cursor.getString(2));
                question.setAnswer_b(cursor.getString(3));
                question.setAnswer_c(cursor.getString(4));
                question.setAnswer_d(cursor.getString(5));
                question.setTruth(Integer.parseInt(cursor.getString(6)));
                questions.add(question);
            } while (cursor.moveToNext());
        }
        Log.i("SQLite DB : Show All  : ", questions.get(0).getIntitule());
        return questions;
    }

    public void addQuestions() {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DROP TABLE " + TABLE_QUESTIONS);
        //db.execSQL(DATABASE_CREATE);
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTITULE, "De quel pays le handball est-il originaire?");
        values.put(COLUMN_ANSWER_A, "France");
        values.put(COLUMN_ANSWER_B, "USA");
        values.put(COLUMN_ANSWER_C, "Espagne");
        values.put(COLUMN_ANSWER_D, "Allemagne");
        values.put(COLUMN_TRUTH, 4);

        db.insert(TABLE_QUESTIONS, null, values);

        db.close();
    }
}
