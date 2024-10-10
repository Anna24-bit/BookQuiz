package org.baltimorecityschools.bookquizappal;

public class Question {
    String qText;
    boolean correctAns;
    String hint;


    public Question(String qqText,boolean ccorrectAns,String hints){
        qText=qqText;
        correctAns=ccorrectAns;
        hint=hints;

    }

    public Question(){
        qText="";
        correctAns=false;
        hint="";

    }


    public String getHints(){

        return hint;

    }

    public void setHints(String aHints){
        hint=aHints;
    }


    public boolean getCorrAns(){

        return correctAns;

    }

    public void setQText(String newqText) {
        qText = newqText;
    }

    public String getQText() {
        return qText;
    }

    public void setCorrect(boolean newAns) {
        this.correctAns = newAns;
    }

    public String toString(){
        return "qText" + qText + "/n"+ "correctAns" +correctAns + hint;
    }


}
