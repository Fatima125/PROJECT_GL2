package entities;

public class TvSeries {

    //attributes
    String genre;
    String title;
    String description;

    //constructors
    public TvSeries(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public TvSeries() {
    }

    //setters && getters
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
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
