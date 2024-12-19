package org.baltimorecityschools.bookquizappal;

public class HighScoreEntry implements Comparable<HighScoreEntry>{

    private String name;
    private Integer score;

    public HighScoreEntry(){
        name="";
        score=0;
    }

    public HighScoreEntry(String Pname, Integer Pscore){
        name=Pname;
        score=Pscore;
    }

    public String getName(){
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    @Override
    public int compareTo(HighScoreEntry other) {
        // Compare books based on their publication year
        return other.score-this.score;
    }

    @Override
    public String toString() {
        return "HighScoreEntry{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
