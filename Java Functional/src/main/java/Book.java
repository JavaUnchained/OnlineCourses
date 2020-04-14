import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private int year;
    private List<String> author;

    public Book(String title, int year, List<String> author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void List(ArrayList<String> author) {
        this.author = author;
    }
}
