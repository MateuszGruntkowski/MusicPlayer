import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Playlist {
	
	private String name;
	private List<Track> tracks;
	
	public Playlist(String name) {
		super();
		this.name = name;
		tracks = new LinkedList<Track>();
	}
	
	public void addTrack(Track track) {
		if(track==null||track.enterTrackName().trim().isEmpty()) {
			System.err.println("The song name cannot be empty!");
		}else {
			tracks.add(track);
		}
	}
	
	public Track enterSongName(int number) {
		return tracks.get(number-1);		
	}
	
	public void displayPlaylist() {
		if(tracks.size()==0) {
			System.out.println("Playlist "+name+" is empty!");
		}else {
			System.out.println("------"+name+"------");
			for(int i = 0; i<tracks.size();i++) {
				System.out.println((i+1)+". "+tracks.get(i).getFullComposition());
			}			 
		}
	}
	
	public void deleteTrack(int number) {
		tracks.remove(number-1);
	}	
	
	public void sortTracks() {
		Collections.sort(tracks);
	}
	
	public Track deleteTrackFromPlaylist(int number) {
		if(number<0||number>tracks.size()) {
			System.err.println("There is no such track.");
			return null;
		}
		return tracks.remove(number-1);
	}

	public String getName() {
		return name;
	}
}
