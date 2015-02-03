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
        return questions;
    }

    public void addQuestions() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_QUESTIONS);
        db.execSQL(DATABASE_CREATE);

        //1iere question
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTITULE, "De quel pays le handball est-il originaire?");
        values.put(COLUMN_ANSWER_A, "France");
        values.put(COLUMN_ANSWER_B, "USA");
        values.put(COLUMN_ANSWER_C, "Espagne");
        values.put(COLUMN_ANSWER_D, "Allemagne");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_QUESTIONS, null, values);

        //2e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "De combien de joueurs se compose une équipe de basket-ball?");
        values.put(COLUMN_ANSWER_A, "3");
        values.put(COLUMN_ANSWER_B, "4");
        values.put(COLUMN_ANSWER_C, "5");
        values.put(COLUMN_ANSWER_D, "6");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_QUESTIONS, null, values);

        //3e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Le jurançon est un vin de quelle région viticole?");
        values.put(COLUMN_ANSWER_A, "Bourgogne");
        values.put(COLUMN_ANSWER_B, "Bordeaux");
        values.put(COLUMN_ANSWER_C, "Jura");
        values.put(COLUMN_ANSWER_D, "Sud Ouest");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_QUESTIONS, null, values);

        //4e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment se nomme une bouteille d'une contenance de 4,5L?");
        values.put(COLUMN_ANSWER_A, "Mathusalem");
        values.put(COLUMN_ANSWER_B, "Balthazar");
        values.put(COLUMN_ANSWER_C, "Melchior");
        values.put(COLUMN_ANSWER_D, "Réhoboram");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_QUESTIONS, null, values);

        //5e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En quel langage le mot bistro signifie-t-il vite?");
        values.put(COLUMN_ANSWER_A, "Arable");
        values.put(COLUMN_ANSWER_B, "Russe");
        values.put(COLUMN_ANSWER_C, "Italien");
        values.put(COLUMN_ANSWER_D, "Turc");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_QUESTIONS, null, values);

        //6e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Qui a inventé la machine à calculer?");
        values.put(COLUMN_ANSWER_A, "Paul");
        values.put(COLUMN_ANSWER_B, "Robert");
        values.put(COLUMN_ANSWER_C, "Philippe");
        values.put(COLUMN_ANSWER_D, "Pascal");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_QUESTIONS, null, values);

        //7e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel est le nombre d'or?");
        values.put(COLUMN_ANSWER_A, "Phi");
        values.put(COLUMN_ANSWER_B, "Epsilon");
        values.put(COLUMN_ANSWER_C, "Gamma");
        values.put(COLUMN_ANSWER_D, "666");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_QUESTIONS, null, values);

        //8e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En quelle année a été prise la première photographie?");
        values.put(COLUMN_ANSWER_A, "1816");
        values.put(COLUMN_ANSWER_B, "1826");
        values.put(COLUMN_ANSWER_C, "1836");
        values.put(COLUMN_ANSWER_D, "1846");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_QUESTIONS, null, values);

        //9e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En 2002, quel était le sport dont la fédération comptait le plus de licenciés au monde?");
        values.put(COLUMN_ANSWER_A, "Volley-ball");
        values.put(COLUMN_ANSWER_B, "Cricket");
        values.put(COLUMN_ANSWER_C, "Football");
        values.put(COLUMN_ANSWER_D, "Baseball");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_QUESTIONS, null, values);

        //10e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "A quelle vitesse atteint-on le mur du son (km/h)?");
        values.put(COLUMN_ANSWER_A, "924");
        values.put(COLUMN_ANSWER_B, "1024");
        values.put(COLUMN_ANSWER_C, "1124");
        values.put(COLUMN_ANSWER_D, "1224");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_QUESTIONS, null, values);

        db.close();
    }
}
