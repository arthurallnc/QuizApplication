package com.example.etudiant.quizapplication;

/**
 * Created by etudiant on 02/02/2015.
 */
public class Question {

    private int id;
    private String intitule;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private int truth;

    public Question(){

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getIntitule(){
        return this.intitule;
    }

    public void setIntitule(String intitule){
        this.intitule = intitule;
    }

    public String getAnswer_a(){
        return this.answer_a;
    }

    public void setAnswer_a(String answer_a){
        this.answer_a = answer_a;
    }

    public String getAnswer_b(){
        return this.answer_b;
    }

    public void setAnswer_b(String answer_b){
        this.answer_b = answer_b;
    }

    public String getAnswer_c(){
        return this.answer_c;
    }

    public void setAnswer_c(String answer_c){
        this.answer_c = answer_c;
    }

    public String getAnswer_d(){
        return this.answer_d;
    }

    public void setAnswer_d(String answer_d){
        this.answer_d = answer_d;
    }

    public int getTruth(){
        return this.truth;
    }

    public void setTruth(int truth){
        this.truth = truth;
    }
}
