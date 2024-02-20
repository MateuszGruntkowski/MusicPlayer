
public class Track implements Comparable<Track> {

	private String name;
	private String author;
	private int year;
	
	public Track(String nazwa,String author,int year) {
		this.name = nazwa;
		this.author=author;
		this.year=year;
	}
	
	public String enterTrackName() {
		return this.name;
	}
	public String enterTrackAuthor() {
		return this.author;
	}
	public int enterTrackYear() {
		return this.year;
	}
	public String getFullComposition(){
		String fullCompositon = this.name + " [" + this.author + "] (" + this.year + ")";
		return fullCompositon;
	}
	
	@Override
	public int compareTo(Track t) {
		return this.enterTrackName().compareTo(t.enterTrackName());
	}

}
