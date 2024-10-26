package Model;

public class Book {
	private String title;
	private String author;
	private int year;
	private int category;
	
	public Book(String title, String author, int year, int category) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                '}';
	}
	
}

