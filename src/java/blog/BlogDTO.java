package blog;

import java.io.Serializable;

public class BlogDTO implements Serializable {

    private int blogID;
    private int accountID;
    private String Body;
    private String Title;
    private String Media;
    private String createTime;

    public BlogDTO() {
    }

    public BlogDTO(int blogID, int accountID, String Body, String Title, String Media, String createTime) {
        this.blogID = blogID;
        this.accountID = accountID;
        this.Body = Body;
        this.Title = Title;
        this.Media = Media;
        this.createTime = createTime;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getMedia() {
        return Media;
    }

    public void setMedia(String Media) {
        this.Media = Media;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BlogDTO{" + "blogID=" + blogID + ", accountID=" + accountID + ", Body=" + Body + ", Title=" + Title + ", Media=" + Media + ", createTime=" + createTime + '}';
    }

}
