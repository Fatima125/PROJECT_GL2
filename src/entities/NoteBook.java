package entities;

public class NoteBook {
    //attributes
    private String title;
    private String description;
    //constructor
    public NoteBook(String Title,String Description)
    {
        this.title =Title;
        this.description =Description;
    }
    public NoteBook(String Title)
    {
        this.title =Title;
    }

    public NoteBook() {

    }

    //getters
    public String getTitle()
    {
        return this.title;
    }
    public String getDescription()
    {
        return this.description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
