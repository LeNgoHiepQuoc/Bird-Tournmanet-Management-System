package feedback;

import account.AccountDTO;
import tournament.TournamentDTO;

public class FeedbackDTO {

    private int feedbackID;
    private int accountID;
    private int tournamentID;
    private String body;
    private TournamentDTO tour;
    private AccountDTO acc;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int accountID, int tournamentID, String body) {
        this.accountID = accountID;
        this.tournamentID = tournamentID;
        this.body = body;
    }
    public FeedbackDTO(int feedbackID, TournamentDTO tour, AccountDTO acc, String body) {
        this.feedbackID = feedbackID;
        this.body = body;
        this.tour = tour;
        this.acc = acc;
    }

    public FeedbackDTO(int feedbackID, int accountID, int tournamentID, String body, TournamentDTO tour, AccountDTO acc) {
        this.feedbackID = feedbackID;
        this.accountID = accountID;
        this.tournamentID = tournamentID;
        this.body = body;
        this.tour = tour;
        this.acc = acc;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TournamentDTO getTour() {
        return tour;
    }

    public void setTour(TournamentDTO tour) {
        this.tour = tour;
    }

    public AccountDTO getAcc() {
        return acc;
    }

    public void setAcc(AccountDTO acc) {
        this.acc = acc;
    }

}
