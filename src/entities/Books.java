package entities;

public class Books {
    //attributes
    private String authorName;
    private String title;
    private String description;

    //constructors

    public Books(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Books() {
    }

    //setters && getters
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
