package practice;

public class BookVo {

	private int bookID;
	private String title;
	private String pubs;
	private String pubDate;
	private int authorID;

	public BookVo() {
	}
	
	public BookVo(int bookID) {
		this.bookID = bookID;
	}
	
	public BookVo(int bookID, String title, String pubs, String pubDate, int authorID) {
		this.bookID = bookID;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorID = authorID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	@Override
	public String toString() {
		return "BookVo [bookID=" + bookID + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate
				+ ", authorID=" + authorID + "]";
	}
	

}
