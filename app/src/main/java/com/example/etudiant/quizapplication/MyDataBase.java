package com.example.etudiant.quizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by etudiant on 02/02/2015.
 */
public class  MyDataBase extends SQLiteOpenHelper{

    private static final String TABLE_EASY_QUESTIONS = "easy_questions";
    private static final String TABLE_MEDIUM_QUESTIONS = "middle_questions";
    private static final String TABLE_HARD_QUESTIONS = "hard_questions";
    private static final String TABLE_EASY_SCORES = "easy_scores";
    private static final String TABLE_MEDIUM_SCORES = "middle_scores";
    private static final String TABLE_HARD_SCORES = "hard_scores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INTITULE = "intitule";
    private static final String COLUMN_ANSWER_A = "answer_a";
    private static final String COLUMN_ANSWER_B = "answer_b";
    private static final String COLUMN_ANSWER_C = "answer_c";
    private static final String COLUMN_ANSWER_D = "answer_d";
    private static final String COLUMN_TRUTH = "truth";
    private static final String COLUMN_SCORE = "score";

    private static final String DATABASE_NAME = "questions.db";
    private static final int DATABASE_VERSION = 6;

    // Database creation sql statement
    private static final String EASY_QUESTION_DATABASE_CREATE = "create table "
            + TABLE_EASY_QUESTIONS
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_INTITULE + " text not null, "
            + COLUMN_ANSWER_A + " text not null, "
            + COLUMN_ANSWER_B + " text not null, "
            + COLUMN_ANSWER_C + " text not null, "
            + COLUMN_ANSWER_D + " text not null, "
            + COLUMN_TRUTH + " integer not null)";

    private static final String MIDDLE_QUESTION_DATABASE_CREATE = "create table "
            + TABLE_MEDIUM_QUESTIONS
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_INTITULE + " text not null, "
            + COLUMN_ANSWER_A + " text not null, "
            + COLUMN_ANSWER_B + " text not null, "
            + COLUMN_ANSWER_C + " text not null, "
            + COLUMN_ANSWER_D + " text not null, "
            + COLUMN_TRUTH + " integer not null)";

    private static final String HARD_QUESTION_DATABASE_CREATE = "create table "
            + TABLE_HARD_QUESTIONS
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_INTITULE + " text not null, "
            + COLUMN_ANSWER_A + " text not null, "
            + COLUMN_ANSWER_B + " text not null, "
            + COLUMN_ANSWER_C + " text not null, "
            + COLUMN_ANSWER_D + " text not null, "
            + COLUMN_TRUTH + " integer not null)";

    private static final String EASY_SCORE_DATABASE_CREATE = "create table "
            + TABLE_EASY_SCORES
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SCORE + " text not null)";

    private static final String MIDDLE_SCORE_DATABASE_CREATE = "create table "
            + TABLE_MEDIUM_SCORES
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SCORE + " text not null)";

    private static final String HARD_SCORE_DATABASE_CREATE = "create table "
            + TABLE_HARD_SCORES
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SCORE + " text not null)";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(EASY_QUESTION_DATABASE_CREATE);
        database.execSQL(MIDDLE_QUESTION_DATABASE_CREATE);
        database.execSQL(HARD_QUESTION_DATABASE_CREATE);
        database.execSQL(EASY_SCORE_DATABASE_CREATE);
        database.execSQL(MIDDLE_SCORE_DATABASE_CREATE);
        database.execSQL(HARD_SCORE_DATABASE_CREATE);
        addEasyQuestions(database);
        addMediumQuestions(database);
        addHardQuestions(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MyDataBase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EASY_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIUM_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HARD_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EASY_SCORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDIUM_SCORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HARD_SCORES);
        onCreate(db);
    }

    public List<Score> showEasyScores() {
        List<Score> scores = new LinkedList<Score>();
        String query = "SELECT * FROM " + TABLE_EASY_SCORES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Score score = null;
        if (cursor.moveToFirst()) {
            do {
                score = new Score();
                score.setId(Integer.parseInt(cursor.getString(0)));
                score.setScore(Integer.parseInt(cursor.getString(1)));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        return scores;
    }

    public List<Score> showMediumScores() {
        List<Score> scores = new LinkedList<Score>();
        String query = "SELECT * FROM " + TABLE_MEDIUM_SCORES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Score score = null;
        if (cursor.moveToFirst()) {
            do {
                score = new Score();
                score.setId(Integer.parseInt(cursor.getString(0)));
                score.setScore(Integer.parseInt(cursor.getString(1)));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        return scores;
    }

    public List<Score> showHardScores() {
        List<Score> scores = new LinkedList<Score>();
        String query = "SELECT * FROM " + TABLE_HARD_SCORES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Score score = null;
        if (cursor.moveToFirst()) {
            do {
                score = new Score();
                score.setId(Integer.parseInt(cursor.getString(0)));
                score.setScore(Integer.parseInt(cursor.getString(1)));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        return scores;
    }

    public void addEasyScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore());
        db.insert(TABLE_EASY_SCORES, null, values);
        db.close();
    }

    public void addMediumScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore());
        db.insert(TABLE_MEDIUM_SCORES, null, values);
        db.close();
    }

    public void addHardScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore());
        db.insert(TABLE_HARD_SCORES, null, values);
        db.close();
    }

    public List<Question> showEasyQuestions() {
        List<Question> questions = new LinkedList<Question>();
        String query = "SELECT * FROM " + TABLE_EASY_QUESTIONS;
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

    public List<Question> showMediumQuestions() {
        List<Question> questions = new LinkedList<Question>();
        String query = "SELECT * FROM " + TABLE_MEDIUM_QUESTIONS;
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

    public List<Question> showHardQuestions() {
        List<Question> questions = new LinkedList<Question>();
        String query = "SELECT * FROM " + TABLE_HARD_QUESTIONS;
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

    public void addEasyQuestions(SQLiteDatabase db) {

        //1iere question
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTITULE, "De quel pays le handball est-il originaire?");
        values.put(COLUMN_ANSWER_A, "France");
        values.put(COLUMN_ANSWER_B, "USA");
        values.put(COLUMN_ANSWER_C, "Espagne");
        values.put(COLUMN_ANSWER_D, "Allemagne");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //2e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "De combien de joueurs se compose une équipe de basket-ball?");
        values.put(COLUMN_ANSWER_A, "3");
        values.put(COLUMN_ANSWER_B, "4");
        values.put(COLUMN_ANSWER_C, "5");
        values.put(COLUMN_ANSWER_D, "6");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //3e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Au Moyen-Âge, comment appelait-on les villages fortifiés?");
        values.put(COLUMN_ANSWER_A, "Tour");
        values.put(COLUMN_ANSWER_B, "Bastide");
        values.put(COLUMN_ANSWER_C, "Château fort");
        values.put(COLUMN_ANSWER_D, "Rempart");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //4e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment se nomme une bouteille d'une contenance de 4,5L?");
        values.put(COLUMN_ANSWER_A, "Mathusalem");
        values.put(COLUMN_ANSWER_B, "Balthazar");
        values.put(COLUMN_ANSWER_C, "Melchior");
        values.put(COLUMN_ANSWER_D, "Réhoboram");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //5e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quelle est la capitale de la Nouvelle-Zélande?");
        values.put(COLUMN_ANSWER_A, "Dublin");
        values.put(COLUMN_ANSWER_B, "Auckland");
        values.put(COLUMN_ANSWER_C, "Wellington");
        values.put(COLUMN_ANSWER_D, "Sydney");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //6e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel film à succès a réuni sur les écrans Sean Connery et Christophe Lambert?");
        values.put(COLUMN_ANSWER_A, "Highlander");
        values.put(COLUMN_ANSWER_B, "Greystoke");
        values.put(COLUMN_ANSWER_C, "Subway");
        values.put(COLUMN_ANSWER_D, "Mad Max");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //7e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel est le nombre d'or?");
        values.put(COLUMN_ANSWER_A, "Phi");
        values.put(COLUMN_ANSWER_B, "Epsilon");
        values.put(COLUMN_ANSWER_C, "Gamma");
        values.put(COLUMN_ANSWER_D, "666");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //8e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En quelle année a été prise la première photographie?");
        values.put(COLUMN_ANSWER_A, "1816");
        values.put(COLUMN_ANSWER_B, "1826");
        values.put(COLUMN_ANSWER_C, "1836");
        values.put(COLUMN_ANSWER_D, "1846");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //9e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel nom porte le logiciel de traitement de texte mis au point par Microsoft?");
        values.put(COLUMN_ANSWER_A, "Access");
        values.put(COLUMN_ANSWER_B, "Word");
        values.put(COLUMN_ANSWER_C, "Excel");
        values.put(COLUMN_ANSWER_D, "PowerPoint");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_EASY_QUESTIONS, null, values);


        //10e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "De quelle série de six films un champion de boxe est-il la vedette?");
        values.put(COLUMN_ANSWER_A, "Ritchie");
        values.put(COLUMN_ANSWER_B, "Rocky");
        values.put(COLUMN_ANSWER_C, "Rambo");
        values.put(COLUMN_ANSWER_D, "Randy");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //11e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Dans le langage familier, comment appelle-t-on la dent du petit enfant?");
        values.put(COLUMN_ANSWER_A, "Marmotte");
        values.put(COLUMN_ANSWER_B, "Menotte");
        values.put(COLUMN_ANSWER_C, "Quenotte");
        values.put(COLUMN_ANSWER_D, "Bouillote");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //12e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel peintre, né en 1844, est également appelé « le Douanier »?");
        values.put(COLUMN_ANSWER_A, "Picasso");
        values.put(COLUMN_ANSWER_B, "Rousseau");
        values.put(COLUMN_ANSWER_C, "Degas");
        values.put(COLUMN_ANSWER_D, "Dali");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //13e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Dans les années 1980, quel groupe musical a chanté le titre \"Shout\"?");
        values.put(COLUMN_ANSWER_A, "Queen");
        values.put(COLUMN_ANSWER_B, "U2");
        values.put(COLUMN_ANSWER_C, "Tears For Fears");
        values.put(COLUMN_ANSWER_D, "Simple Minds");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //14e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quelle est la seule valeur à la roulette à porter la couleur verte?");
        values.put(COLUMN_ANSWER_A, "0");
        values.put(COLUMN_ANSWER_B, "50");
        values.put(COLUMN_ANSWER_C, "40");
        values.put(COLUMN_ANSWER_D, "13");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //15e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quelle est la plus petite unité de mémoire utilisable sur un ordinateur?");
        values.put(COLUMN_ANSWER_A, "Byte");
        values.put(COLUMN_ANSWER_B, "Mega");
        values.put(COLUMN_ANSWER_C, "Bit");
        values.put(COLUMN_ANSWER_D, "Giga");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //16e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel insecte appelle-t-on aussi « la bête à bon dieu »?");
        values.put(COLUMN_ANSWER_A, "Luciole");
        values.put(COLUMN_ANSWER_B, "Coccinelle");
        values.put(COLUMN_ANSWER_C, "Scarabée");
        values.put(COLUMN_ANSWER_D, "Libellule");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //17e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Qui fut le premier européen à arriver aux Philippines?");
        values.put(COLUMN_ANSWER_A, "Magellan");
        values.put(COLUMN_ANSWER_B, "Colomb");
        values.put(COLUMN_ANSWER_C, "Vasco de Gama");
        values.put(COLUMN_ANSWER_D, "Mauperthuis");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //18e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Combien de temps dure le mandat du président des États-Unis?");
        values.put(COLUMN_ANSWER_A, "4 ans");
        values.put(COLUMN_ANSWER_B, "7 ans");
        values.put(COLUMN_ANSWER_C, "5 ans");
        values.put(COLUMN_ANSWER_D, "6 ans");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //19e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Dans quelle ville se trouve la fontaine de Trevi?");
        values.put(COLUMN_ANSWER_A, "Rome");
        values.put(COLUMN_ANSWER_B, "Venise");
        values.put(COLUMN_ANSWER_C, "Barcelone");
        values.put(COLUMN_ANSWER_D, "Seville");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

        //20e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel animal est le plus rapide à terre?");
        values.put(COLUMN_ANSWER_A, "Léopard");
        values.put(COLUMN_ANSWER_B, "Lynx");
        values.put(COLUMN_ANSWER_C, "Guépard");
        values.put(COLUMN_ANSWER_D, "Panthère");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_EASY_QUESTIONS, null, values);

    }

    public void addMediumQuestions(SQLiteDatabase db) {

        //1iere question
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTITULE, "Le jurançon est un vin de quelle région viticole?");
        values.put(COLUMN_ANSWER_A, "Bourgogne");
        values.put(COLUMN_ANSWER_B, "Bordeaux");
        values.put(COLUMN_ANSWER_C, "Jura");
        values.put(COLUMN_ANSWER_D, "Sud Ouest");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //2e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "A quelle vitesse atteint-on le mur du son (km/h)?");
        values.put(COLUMN_ANSWER_A, "924");
        values.put(COLUMN_ANSWER_B, "1024");
        values.put(COLUMN_ANSWER_C, "1124");
        values.put(COLUMN_ANSWER_D, "1224");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //3e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En 2002, quel était le sport dont la fédération comptait le plus de licenciés au monde?");
        values.put(COLUMN_ANSWER_A, "Volley-ball");
        values.put(COLUMN_ANSWER_B, "Cricket");
        values.put(COLUMN_ANSWER_C, "Football");
        values.put(COLUMN_ANSWER_D, "Baseball");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //4e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Qui a inventé la machine à calculer?");
        values.put(COLUMN_ANSWER_A, "Paul");
        values.put(COLUMN_ANSWER_B, "Robert");
        values.put(COLUMN_ANSWER_C, "Philippe");
        values.put(COLUMN_ANSWER_D, "Pascal");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //5e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En quel langage le mot bistro signifie-t-il vite?");
        values.put(COLUMN_ANSWER_A, "Arable");
        values.put(COLUMN_ANSWER_B, "Russe");
        values.put(COLUMN_ANSWER_C, "Italien");
        values.put(COLUMN_ANSWER_D, "Turc");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //6e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment appelle-t-on le fruit du plaqueminier?");
        values.put(COLUMN_ANSWER_A, "Kakou");
        values.put(COLUMN_ANSWER_B, "Kacha");
        values.put(COLUMN_ANSWER_C, "Kaki");
        values.put(COLUMN_ANSWER_D, "Kali");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //7e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel pays a pour capitale Katmandou?");
        values.put(COLUMN_ANSWER_A, "Népal");
        values.put(COLUMN_ANSWER_B, "Tibet");
        values.put(COLUMN_ANSWER_C, "Corée du Sud");
        values.put(COLUMN_ANSWER_D, "Pakistan");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //8e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "À quel groupe musical international doit-on la bande originale du film \"Flash Gordon\"?");
        values.put(COLUMN_ANSWER_A, "Queen");
        values.put(COLUMN_ANSWER_B, "Yes");
        values.put(COLUMN_ANSWER_C, "Led Zeppelin");
        values.put(COLUMN_ANSWER_D, "The Doors");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //9e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "À quelle classe animale le scorpion appartient-il?");
        values.put(COLUMN_ANSWER_A, "Mammifère");
        values.put(COLUMN_ANSWER_B, "Reptile");
        values.put(COLUMN_ANSWER_C, "Arachnide");
        values.put(COLUMN_ANSWER_D, "Insecte");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //10e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel oiseau palmipède a pour particularité de construire un nid flottant?");
        values.put(COLUMN_ANSWER_A, "Grèle");
        values.put(COLUMN_ANSWER_B, "Grève");
        values.put(COLUMN_ANSWER_C, "Grèbe");
        values.put(COLUMN_ANSWER_D, "Grène");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //11e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment appelle-t-on le versant de la montagne non situé au soleil?");
        values.put(COLUMN_ANSWER_A, "Adret");
        values.put(COLUMN_ANSWER_B, "Ubac");
        values.put(COLUMN_ANSWER_C, "Etant");
        values.put(COLUMN_ANSWER_D, "Ressac");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //12e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Dans quel pays se trouve le circuit automobile de Zandvoort?");
        values.put(COLUMN_ANSWER_A, "Belgique");
        values.put(COLUMN_ANSWER_B, "Suisse");
        values.put(COLUMN_ANSWER_C, "Pays-Bas");
        values.put(COLUMN_ANSWER_D, "France");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //13e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel roman George Orwell a-t-il écrit en 1948?");
        values.put(COLUMN_ANSWER_A, "2001");
        values.put(COLUMN_ANSWER_B, "2010");
        values.put(COLUMN_ANSWER_C, "1984");
        values.put(COLUMN_ANSWER_D, "1948");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //14e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En quoi un nihiliste croit-il?");
        values.put(COLUMN_ANSWER_A, "La chance");
        values.put(COLUMN_ANSWER_B, "Tout");
        values.put(COLUMN_ANSWER_C, "Rien");
        values.put(COLUMN_ANSWER_D, "Lui-même");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //15e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Avec quel autre animal vit généralement une oie?");
        values.put(COLUMN_ANSWER_A, "Canard");
        values.put(COLUMN_ANSWER_B, "Jars");
        values.put(COLUMN_ANSWER_C, "Cygne");
        values.put(COLUMN_ANSWER_D, "Poule");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //16e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quelle est la plus grande tortue marine connue à ce jour?");
        values.put(COLUMN_ANSWER_A, "Franche");
        values.put(COLUMN_ANSWER_B, "Ninja");
        values.put(COLUMN_ANSWER_C, "Luth");
        values.put(COLUMN_ANSWER_D, "Argneuse");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //17e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Avec quoi mesure-t-on la profondeur des océans?");
        values.put(COLUMN_ANSWER_A, "Sextant");
        values.put(COLUMN_ANSWER_B, "Sonar");
        values.put(COLUMN_ANSWER_C, "Radar");
        values.put(COLUMN_ANSWER_D, "Compteur Geiger");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //18e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En France, dans quelle ville se trouve l'adresse du père Noël?");
        values.put(COLUMN_ANSWER_A, "Strasbourg");
        values.put(COLUMN_ANSWER_B, "Paris");
        values.put(COLUMN_ANSWER_C, "Libourne");
        values.put(COLUMN_ANSWER_D, "Narbonne");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //18e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment se nomme le plus grand spécimen de chouette?");
        values.put(COLUMN_ANSWER_A, "Grand compte");
        values.put(COLUMN_ANSWER_B, "Grand duc");
        values.put(COLUMN_ANSWER_C, "Grand roi");
        values.put(COLUMN_ANSWER_D, "Grand prince");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //19e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Sur quelle lettre ne peut-on pas mettre de tréma?");
        values.put(COLUMN_ANSWER_A, "E");
        values.put(COLUMN_ANSWER_B, "I");
        values.put(COLUMN_ANSWER_C, "O");
        values.put(COLUMN_ANSWER_D, "U");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);

        //20e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Que signifie le terme Karaoke en japonais?");
        values.put(COLUMN_ANSWER_A, "Chanson");
        values.put(COLUMN_ANSWER_B, "Microphone");
        values.put(COLUMN_ANSWER_C, "Parole");
        values.put(COLUMN_ANSWER_D, "Vide");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_MEDIUM_QUESTIONS, null, values);
    }

    public void addHardQuestions(SQLiteDatabase db) {

        //1iere question
        ContentValues values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quelle est l'unité de mesure de la force d'un piment?");
        values.put(COLUMN_ANSWER_A, "Thermoptim");
        values.put(COLUMN_ANSWER_B, "Scoville");
        values.put(COLUMN_ANSWER_C, "Cherit");
        values.put(COLUMN_ANSWER_D, "Hauy");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //2e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Le \"Vosne-Romanée\" est un vin de quelle région viticole?");
        values.put(COLUMN_ANSWER_A, "Languedoc");
        values.put(COLUMN_ANSWER_B, "Bordelais");
        values.put(COLUMN_ANSWER_C, "Beujolais");
        values.put(COLUMN_ANSWER_D, "Bourgogne");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //3e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel est la valeur du pH du Coca-Cola?");
        values.put(COLUMN_ANSWER_A, "2,3");
        values.put(COLUMN_ANSWER_B, "4,5");
        values.put(COLUMN_ANSWER_C, "5,3");
        values.put(COLUMN_ANSWER_D, "7");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //4e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment se nomme le signe \"&\"?");
        values.put(COLUMN_ANSWER_A, "Esperluette");
        values.put(COLUMN_ANSWER_B, "Heperluette");
        values.put(COLUMN_ANSWER_C, "Eperluette");
        values.put(COLUMN_ANSWER_D, "Eperluète");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //5e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment s'appelle le petit du zèbre?");
        values.put(COLUMN_ANSWER_A, "Zèbreux");
        values.put(COLUMN_ANSWER_B, "Zèbrotin");
        values.put(COLUMN_ANSWER_C, "Zèbriot");
        values.put(COLUMN_ANSWER_D, "Zèbreau");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //6e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Sur quelle matière première a eu lieu la première bulle spéculative?");
        values.put(COLUMN_ANSWER_A, "Or");
        values.put(COLUMN_ANSWER_B, "Tulipe");
        values.put(COLUMN_ANSWER_C, "Bois");
        values.put(COLUMN_ANSWER_D, "Soufre");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //7e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Où pleut-il le plus en France?");
        values.put(COLUMN_ANSWER_A, "Biarritz");
        values.put(COLUMN_ANSWER_B, "Brest");
        values.put(COLUMN_ANSWER_C, "Besançon");
        values.put(COLUMN_ANSWER_D, "Limoges");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //8e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "De quelle nationalité sont les gardes du Vatican?");
        values.put(COLUMN_ANSWER_A, "Suisse");
        values.put(COLUMN_ANSWER_B, "Serbe");
        values.put(COLUMN_ANSWER_C, "Italien");
        values.put(COLUMN_ANSWER_D, "Turque");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //9e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel était le surnom du dictateur Franco?");
        values.put(COLUMN_ANSWER_A, "El General");
        values.put(COLUMN_ANSWER_B, "El Grande");
        values.put(COLUMN_ANSWER_C, "El Caudillo");
        values.put(COLUMN_ANSWER_D, "El Tapioca");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //10e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel est le nom de la ville française le plus court?");
        values.put(COLUMN_ANSWER_A, "A");
        values.put(COLUMN_ANSWER_B, "E");
        values.put(COLUMN_ANSWER_C, "U");
        values.put(COLUMN_ANSWER_D, "Y");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //11e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel est le nom scientifique des gargouillis émis par les intestins?");
        values.put(COLUMN_ANSWER_A, "Diactèmes");
        values.put(COLUMN_ANSWER_B, "Flarugymes");
        values.put(COLUMN_ANSWER_C, "Astimydes");
        values.put(COLUMN_ANSWER_D, "Borborygmes");
        values.put(COLUMN_TRUTH, 4);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //12e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "De quel membre de la famille de Nicols Hulot, le M. Hulot de Jacques Tati s'est-il inspiré?");
        values.put(COLUMN_ANSWER_A, "Père");
        values.put(COLUMN_ANSWER_B, "Grand-père");
        values.put(COLUMN_ANSWER_C, "Oncle");
        values.put(COLUMN_ANSWER_D, "Cousin");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //13e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Combien de personnes sont nécessaires pour lancer une « ola »?");
        values.put(COLUMN_ANSWER_A, "25");
        values.put(COLUMN_ANSWER_B, "50");
        values.put(COLUMN_ANSWER_C, "75");
        values.put(COLUMN_ANSWER_D, "100");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //14e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "En quelle année, le mois de janvier est-il devenu le premier mois de l'année?");
        values.put(COLUMN_ANSWER_A, "46 avant JC");
        values.put(COLUMN_ANSWER_B, "352");
        values.put(COLUMN_ANSWER_C, "646");
        values.put(COLUMN_ANSWER_D, "812");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //15e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Les carottes contiennent de la vitamine A utile pour lutter contre quelle maladie?");
        values.put(COLUMN_ANSWER_A, "Héméralopie");
        values.put(COLUMN_ANSWER_B, "Bronhumite");
        values.put(COLUMN_ANSWER_C, "Dépressionnite");
        values.put(COLUMN_ANSWER_D, "Arthrosopie");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //16e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "A quel élément se rapporte la loi de Godwin?");
        values.put(COLUMN_ANSWER_A, "Christianisme");
        values.put(COLUMN_ANSWER_B, "Lettrisme");
        values.put(COLUMN_ANSWER_C, "Nazisme");
        values.put(COLUMN_ANSWER_D, "Socialisme");
        values.put(COLUMN_TRUTH, 3);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //17e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Quel est le diamètre d'un CD?");
        values.put(COLUMN_ANSWER_A, "11 cm");
        values.put(COLUMN_ANSWER_B, "12 cm");
        values.put(COLUMN_ANSWER_C, "13 cm");
        values.put(COLUMN_ANSWER_D, "14 cm");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //18e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Comment se nomme le père Noël au Danemark?");
        values.put(COLUMN_ANSWER_A, "Julinisse");
        values.put(COLUMN_ANSWER_B, "Jouluvana");
        values.put(COLUMN_ANSWER_C, "Joulupukki");
        values.put(COLUMN_ANSWER_D, "Jultomte");
        values.put(COLUMN_TRUTH, 1);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //19e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Combien pèse une fraise tagada Haribo?");
        values.put(COLUMN_ANSWER_A, "3,39 g");
        values.put(COLUMN_ANSWER_B, "5,39 g");
        values.put(COLUMN_ANSWER_C, "7,39 g");
        values.put(COLUMN_ANSWER_D, "9,39 g");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_HARD_QUESTIONS, null, values);

        //20e question
        values = new ContentValues();
        values.put(COLUMN_INTITULE, "Le prénom de César était?");
        values.put(COLUMN_ANSWER_A, "Jules");
        values.put(COLUMN_ANSWER_B, "Caïus");
        values.put(COLUMN_ANSWER_C, "Ignatius");
        values.put(COLUMN_ANSWER_D, "Marcellus");
        values.put(COLUMN_TRUTH, 2);
        db.insert(TABLE_HARD_QUESTIONS, null, values);
    }

    public void createTables(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(EASY_QUESTION_DATABASE_CREATE);
        database.execSQL(MIDDLE_QUESTION_DATABASE_CREATE);
        database.execSQL(HARD_QUESTION_DATABASE_CREATE);
        database.execSQL(EASY_SCORE_DATABASE_CREATE);
        database.execSQL(MIDDLE_SCORE_DATABASE_CREATE);
        database.execSQL(HARD_SCORE_DATABASE_CREATE);
        database.close();
    }

    public void restartEasyScoreTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_EASY_SCORES);
        db.execSQL(EASY_SCORE_DATABASE_CREATE);
        db.close();
    }

    public void restartMediumScoreTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_MEDIUM_SCORES);
        db.execSQL(MIDDLE_SCORE_DATABASE_CREATE);
        db.close();
    }

    public void restartHardScoreTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_HARD_SCORES);
        db.execSQL(HARD_SCORE_DATABASE_CREATE);
        db.close();
    }
}
