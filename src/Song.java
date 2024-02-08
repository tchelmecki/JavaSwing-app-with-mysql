public class Song {
    Integer id;
    String title;
    String author;
    String genre;
    String album;

    public Song(Integer id, String title, String author, String genre, String album) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.album = album;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}
