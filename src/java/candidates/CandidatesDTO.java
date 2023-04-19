package candidates;

import bird.BirdDTO;
import java.io.Serializable;
import registrationform.RegistrationFormDTO;
import round.RoundDTO;
import tournament.TournamentDTO;

public class CandidatesDTO implements Serializable {

    private int candidatesID;
    private RoundDTO round;
    private TournamentDTO tournament;
    private RegistrationFormDTO form;
    private BirdDTO bird;
    private int score;
    private String result;
    private int candidatesStatus;

    public CandidatesDTO() {
    }

    public CandidatesDTO(int candidatesID, RoundDTO round, TournamentDTO tournament, RegistrationFormDTO form, BirdDTO bird, int score, String result, int candidatesStatus) {
        this.candidatesID = candidatesID;
        this.round = round;
        this.tournament = tournament;
        this.form = form;
        this.bird = bird;
        this.score = score;
        this.result = result;
        this.candidatesStatus = candidatesStatus;
    }

    public int getCandidatesID() {
        return candidatesID;
    }

    public void setCandidatesID(int candidatesID) {
        this.candidatesID = candidatesID;
    }

    public RoundDTO getRound() {
        return round;
    }

    public void setRound(RoundDTO round) {
        this.round = round;
    }

    public TournamentDTO getTournament() {
        return tournament;
    }

    public void setTournament(TournamentDTO tournament) {
        this.tournament = tournament;
    }

    public RegistrationFormDTO getForm() {
        return form;
    }

    public void setForm(RegistrationFormDTO form) {
        this.form = form;
    }

    public BirdDTO getBird() {
        return bird;
    }

    public void setBird(BirdDTO bird) {
        this.bird = bird;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCandidatesStatus() {
        return candidatesStatus;
    }

    public void setCandidatesStatus(int candidatesStatus) {
        this.candidatesStatus = candidatesStatus;
    }
}
