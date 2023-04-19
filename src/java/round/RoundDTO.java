package round;

import java.io.Serializable;
import tournament.TournamentDTO;

public class RoundDTO implements Serializable {

    private int roundID;
    private TournamentDTO tournament;
    private String roundName;
    private String typeOfRound;
    private int birdPass;
    private int birdAttend;
    private int roundStatus;

    public RoundDTO() {
    }

    public RoundDTO(int roundID, TournamentDTO tournament, String roundName, String typeOfRound, int birdPass, int birdAttend, int roundStatus) {
        this.roundID = roundID;
        this.tournament = tournament;
        this.roundName = roundName;
        this.typeOfRound = typeOfRound;
        this.birdPass = birdPass;
        this.birdAttend = birdAttend;
        this.roundStatus = roundStatus;
    }

    public int getRoundID() {
        return roundID;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getTypeOfRound() {
        return typeOfRound;
    }

    public void setTypeOfRound(String typeOfRound) {
        this.typeOfRound = typeOfRound;
    }

    public int getBirdPass() {
        return birdPass;
    }

    public void setBirdPass(int birdPass) {
        this.birdPass = birdPass;
    }

    public int getBirdAttend() {
        return birdAttend;
    }

    public void setBirdAttend(int birdAttend) {
        this.birdAttend = birdAttend;
    }

    public int getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(int roundStatus) {
        this.roundStatus = roundStatus;
    }

}
