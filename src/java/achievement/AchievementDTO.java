package achievement;

import bird.BirdDTO;
import java.io.Serializable;

public class AchievementDTO implements Serializable {

    private int achievementID;
    private int birdID;
    private BirdDTO bird;
    private String description;
    private String medals;
    private int totalScore;
    private int top;

    public AchievementDTO(){
        
    }
    public AchievementDTO(int achievementID, int birdID, BirdDTO bird, String description, String medals, int totalScore, int top) {
        this.achievementID = achievementID;
        this.birdID = birdID;
        this.bird = bird;
        this.description = description;
        this.medals = medals;
        this.totalScore = totalScore;
        this.top = top;
    }
    
    public AchievementDTO(int birdID, String description, String medals, int totalScore, int top) {
        this.birdID = birdID;
        this.description = description;
        this.medals = medals;
        this.totalScore = totalScore;
        this.top = top;
    }

    public int getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(int achievementID) {
        this.achievementID = achievementID;
    }

    public BirdDTO getBird() {
        return bird;
    }

    public void setBird(BirdDTO bird) {
        this.bird = bird;
    }
    
    public AchievementDTO(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getBirdID() {
        return birdID;
    }

    public void setBirdID(int birdID) {
        this.birdID = birdID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedals() {
        return medals;
    }

    public void setMedals(String medals) {
        this.medals = medals;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "AchievementDTO{" + "birdID=" + birdID + ", description=" + description + ", medals=" + medals + ", totalScore=" + totalScore + ", top=" + top + '}';
    }

    
}
