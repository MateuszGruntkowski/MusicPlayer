import java.util.HashMap;
import java.util.Scanner;

public class Player {
//	private Playlista p1;
//	private Playlista p2;
	private HashMap<String, Playlist> playlists = new HashMap<>();
	private Scanner sc = new Scanner(System.in);

	private final static String Rock = "ROCK";
	private final static String Pop = "POP";

	private boolean on = true;

	public Player() {
//		p1 = new Playlista("ROCK");
//		p2 = new Playlista("POP");

		playlists.put(Rock, new Playlist("ROCK"));
		playlists.put(Pop, new Playlist("POP"));

		init();
	}

	private void init() {
		playlists.get(Rock).addTrack(new Track("Smoke on the Water", "Deep Purple", 1972));
		playlists.get(Rock).addTrack(new Track("Child in Time", "Deep Purple", 1970));
		playlists.get(Rock).addTrack(new Track("Fortunate Son", "CCR", 1969));
		playlists.get(Rock).addTrack(new Track("Have You Ever Seen The Rain?", "CCR", 1970));
		playlists.get(Rock).addTrack(new Track("Fear of the Dark", "Iron Maiden", 1992));
		playlists.get(Rock).addTrack(new Track("Aces High", "Iron Maiden", 1984));
		playlists.get(Rock).addTrack(new Track("The Trooper", "Iron Maiden", 1983));

		playlists.get(Pop).addTrack(new Track("Tylko z Tobą Chcę Być Sobą", "Łukasz Zagrobelny", 2014));
		playlists.get(Pop).addTrack(new Track("Love Me Like You Do", "Ellie Goulding", 2014));
		playlists.get(Pop).addTrack(new Track("Jutro", "LemON", 2014));
		playlists.get(Pop).addTrack(new Track("Cool Kids", "Echosmith", 2013));
		playlists.get(Pop).addTrack(new Track("Perdoname (feat. Adrian Delgado & DyCy)", "Deorro", 2014));
		playlists.get(Pop).addTrack(new Track("What are you waiting for", "Nickelback", 2014));
		playlists.get(Pop).addTrack(new Track("Lips Are Movin'", "Meghan Trainor", 2015));
		playlists.get(Pop).addTrack(new Track("Kalejdoskop szczęścia", "Andrzej Piaseczny", 2015));
	}

	public void turnOn() {
		on = true;
		while (on) {
			displayMenu();
			int option = getOption();
			executeOption(option);
		}
	}

	public int getOption() {
		String sOption = sc.nextLine();
		int intOption = Integer.parseInt(sOption);

		return intOption;
	}

	public void executeOption(int option) {
		switch (option) {
		case 0:
			displayPlaylists();
			break;
		case 1:
			addPlaylist();
			break;
		case 2:
			displayPlaylist();
			break;
		case 3:
			sortPlaylist();
			break;
		case 4:
			deletePlaylist();
			break;
		case 5:
			addNewTrack();
			break;
		case 6:
			moveTrack();
			break;
		case 7:
			copyTrack();
			break;
		case 8:
			deleteTrack();
			break;
		case 9:
			System.exit(0);
		default:
			System.err.println("There is no such option.");
		}
	}

	public void displayMenu() {
		{
			StringBuilder builder = new StringBuilder();

			builder.append("What you want to do? Select an option:\n");
			builder.append("\t(0) See available playlists\n");
			builder.append("\t(1) Add playlist\n");
			builder.append("\t(2) Display playlist\n");
			builder.append("\t(3) Sort playlist\n");
			builder.append("\t(4) Delete playlist\n");
			builder.append("\t(5) Add new track\n");
			builder.append("\t(6) Move track\n");
			builder.append("\t(7) Copy track\n");
			builder.append("\t(8) Delete track\n");
			builder.append("\t(9) Turn off the player\n");
			System.out.print(builder.toString());

		}

	}

	public void addPlaylist() {
		System.out.println("Enter the name of the playlist you want to add: ");
		String playlistToAdd = sc.nextLine();
		Playlist playlist = new Playlist(playlistToAdd.toUpperCase());
		boolean nameExists = playlists.containsKey(playlistToAdd.toUpperCase());

		if (nameExists) {
			System.out.println("Playlist called " + playlistToAdd.toUpperCase() + " already exists");
		} else {
			playlists.put(playlistToAdd.toUpperCase(), playlist);
			System.out.println("Playlist added");
		}
	}

	public void displayPlaylist() {
		System.out.println("Which playlist do you want to see?: ");
		displayPlaylists();
		String playlistToDisplay = sc.nextLine();
		Playlist playlist = playlists.get(playlistToDisplay.toUpperCase());

		if (playlist != null) {
			playlist.displayPlaylist();
		} else {
			System.out.println("The playlist does not exist");
		}
	}

	public void sortPlaylist() {
		System.out.println("Select the playlist you want to sort: ");
		displayPlaylists();
		String playlistToSort = sc.nextLine();
		Playlist playlist = playlists.get(playlistToSort.toUpperCase());

		if (playlist != null) {
			playlist.sortTracks();
		} else {
			System.out.println("The playlist does not exist");
		}
	}

	public void displayPlaylists() {
		for (String playlistName : playlists.keySet()) {
			System.out.println("\t(" + playlistName + ")");
		}
	}

	public void deletePlaylist() {
		System.out.println("Select the playlist you want to delete: ");
		displayPlaylists();
		String playlistToRemove = sc.nextLine();
		Playlist playlista = playlists.remove(playlistToRemove.toUpperCase());

		if (playlista != null) {
			System.out.println("Playlist deleted");
		} else {
			System.out.println("The playlist does not exist");
		}
	}

	public void addNewTrack() {
		Playlist pl = choosePlaylist();
		if (pl != null) {
			System.out.println("Enter the title of the track:");
			String title = sc.nextLine();
			System.out.println("Enter the artist of the track:");
			String author = sc.nextLine();
			System.out.println("Enter the year of release of the track: ");
			int publishedYear = sc.nextInt();
			sc.nextLine();
			pl.addTrack(new Track(title, author, publishedYear));
			pl.displayPlaylist();
			System.out.println("New track added");
		}
	}

	public void moveTrack() {
		System.out.println("Select source playlist: ");
		Playlist p1 = choosePlaylist();
		System.out.println("Select target playlist: ");
		Playlist p2 = choosePlaylist();
		if (p1 != null) {
			p1.displayPlaylist();
			System.out.println("Which song do you want to move to this playlist?: ");
			byte trackNumber = sc.nextByte();
			sc.nextLine();
			Track utwor = p1.deleteTrackFromPlaylist(trackNumber);
			if (utwor != null) {
				p2.addTrack(utwor);
				System.out.println("Track moved");
			}
		}
	}

	public void copyTrack() {
		System.out.println("Select source playlist: ");
		Playlist p1 = choosePlaylist();
		System.out.println("Select target playlist: ");
		Playlist p2 = choosePlaylist();
		if (p1 != null) {
			p1.displayPlaylist();
			System.out.println("Which song do you want to copy?: ");
			byte trackNumber = sc.nextByte();
			sc.nextLine();
			Track track = p1.enterSongName(trackNumber);
			if (track != null) {
				p2.addTrack(track);
				System.out.println("Track copied");
			}
		}
	}

	public void deleteTrack() {
		Playlist pl = choosePlaylist();
		if (pl != null) {
			pl.displayPlaylist();
			System.out.println("Which song do you want to delete?: ");
			byte trackNumber = sc.nextByte();
			sc.nextLine();
			pl.deleteTrack(trackNumber);
			System.out.println("Track deleted");
		}
	}

	public Playlist choosePlaylist() {
		System.out.println("Which playlist do you want to use? Select an option: ");
		for (String trackName : playlists.keySet()) {
			System.out.println("\t(" + trackName + ")");
		}
		//System.out.println("?");
		String trackName = sc.nextLine();
		//sc.nextLine();
		return playlists.get(trackName.toUpperCase());
	}
}
