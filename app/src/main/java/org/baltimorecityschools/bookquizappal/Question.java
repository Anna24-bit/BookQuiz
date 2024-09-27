package org.baltimorecityschools.bookquizappal;

public class Question {
    String qText;
    boolean correctAns;


    public Question(String qqText,boolean ccorrectAns){
        qText=qqText;
        correctAns=ccorrectAns;

    }

    public Question(){
        qText="";
        correctAns=false;

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
        return "qText" + qText + "/n"+ "correctAns" +correctAns;
    }
}
