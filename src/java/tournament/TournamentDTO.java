package tournament;

import birdcategories.BirdCategoriesDTO;
import java.io.Serializable;

public class TournamentDTO implements Serializable {

    private int tournamentID;
    private String tournamentName;
    private BirdCategoriesDTO category;
    private int categoriesID;
    private String description;
    private String sponsor;
    private String prize;
    private int minParticipant;
    private int maxParticipant;
    private String dateTime;
    private String fee;
    private String location;
    private String image;
    private int tournamentStatus;

    public TournamentDTO(int tournamentID, BirdCategoriesDTO category, String tournamentName, String description, String sponsor, String prize, int minParticipant, int maxParticipant, String dateTime, String fee, String location, String image, int tournamentStatus) {
        this.tournamentID = tournamentID;
        this.category = category;
        this.tournamentName = tournamentName;
        this.description = description;
        this.sponsor = sponsor;
        this.prize = prize;
        this.minParticipant = minParticipant;
        this.maxParticipant = maxParticipant;
        this.dateTime = dateTime;
        this.fee = fee;
        this.location = location;
        this.image = image;
        this.tournamentStatus = tournamentStatus;
    }

    public TournamentDTO(int tournamentID, String tournamentName, String dateTime, int tournamentStatus, int minParticipant, int maxParticipant, String fee, String prize, String image) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.dateTime = dateTime;
        this.tournamentStatus = tournamentStatus;
        this.minParticipant = minParticipant;
        this.maxParticipant = maxParticipant;
        this.fee = fee;
        this.prize = prize;
        this.image = image;
    }

    public TournamentDTO(int tournamentID, String image, String tournamentName, int tournamentStatus, String dateTime, String location, String fee, String prize, int minParticipant, String sponsor) {
        this.tournamentID = tournamentID;
        this.image = image;
        this.tournamentName = tournamentName;
        this.tournamentStatus = tournamentStatus;
        this.dateTime = dateTime;
        this.location = location;
        this.fee = fee;
        this.prize = prize;
        this.minParticipant = minParticipant;
        this.sponsor = sponsor;
    }

    public TournamentDTO(int tournamentID, String location, String fee, int tournamentStatus, String image, String tournamentName, String dateTime) {
        this.tournamentID = tournamentID;
        this.location = location;
        this.fee = fee;
        this.tournamentStatus = tournamentStatus;
        this.image = image;
        this.tournamentName = tournamentName;
        this.dateTime = dateTime;

    }

    public TournamentDTO() {
    }

    public TournamentDTO(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public TournamentDTO(int tournamentID, String tournamentName, int categoriesID, String description, String sponsor, String prize, int minParticipant, int maxParticipant, String dateTime, String fee, String image, String location, int tournamentStatus) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.categoriesID = categoriesID;
        this.description = description;
        this.sponsor = sponsor;
        this.prize = prize;
        this.minParticipant = minParticipant;
        this.maxParticipant = maxParticipant;
        this.dateTime = dateTime;
        this.fee = fee;
        this.image = image;
        this.location = location;
        this.tournamentStatus = tournamentStatus;
    }

    public TournamentDTO(int tournamentID, String tournamentName, String location, String fee, String dateTime, int minParticipant, int tournamentStatus) {
        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.location = location;
        this.fee = fee;
        this.dateTime = dateTime;
        this.minParticipant = minParticipant;
        this.tournamentStatus = tournamentStatus;
    }

    public TournamentDTO(String tournamentName, int minParticipant, int maxParticipant, String dateTime, String location, String fee, String sponsor, String prize, int categoriesID, String image, int tournamentStatus, String description) {
        this.tournamentName = tournamentName;
        this.minParticipant = minParticipant;
        this.maxParticipant = maxParticipant;
        this.dateTime = dateTime;
        this.location = location;
        this.fee = fee;
        this.sponsor = sponsor;
        this.prize = prize;
        this.categoriesID = categoriesID;
        this.image = image;
        this.tournamentStatus = tournamentStatus;
        this.description = description;
    }

    public BirdCategoriesDTO getCategory() {
        return category;
    }

    public void setCategory(BirdCategoriesDTO category) {
        this.category = category;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getBirdCategory() {
        return categoriesID;
    }

    public void setBirdCategory(int categoriesID) {
        this.categoriesID = categoriesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getMinParticipant() {
        return minParticipant;
    }

    public void setMinParticipant(int minParticipant) {
        this.minParticipant = minParticipant;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTournamentStatus() {
        return tournamentStatus;
    }

    public void setTournamentStatus(int tournamentStatus) {
        this.tournamentStatus = tournamentStatus;
    }

    public int getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        this.categoriesID = categoriesID;
    }

    @Override
    public String toString() {
        return "TournamentDTO{" + "tournamentID=" + tournamentID + ", tournamentName=" + tournamentName + ", categoriesID=" + categoriesID + ", description=" + description + ", sponsor=" + sponsor + ", prize=" + prize + ", minParticipant=" + minParticipant + ", maxParticipant=" + maxParticipant + ", dateTime=" + dateTime + ", fee=" + fee + ", location=" + location + ", image=" + image + ", tournamentStatus=" + tournamentStatus + '}';
    }

}
