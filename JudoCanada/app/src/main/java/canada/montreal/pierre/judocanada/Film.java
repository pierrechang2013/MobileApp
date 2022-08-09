package canada.montreal.pierre.judocanada;

import android.os.Parcel;
import android.os.Parcelable;

class Film implements Parcelable {

    /*
     * {"Title": "Year" "Rated": "Released": "Runtime" "Genre" "Director" "Writer "Actors ,"Plot" ,"Language": "Country" ,
     * "Awards"
     * "Metascore" ,"imdbRating": "imdbVotes" "imdbID": ,"Type": "DVD": "BoxOffice" "Production": ,"Website"  }
     * */

    String  Title ,Year,Rated,Released,Runtime,Genre,Director,Writer,Actors ,Plot,Language,Country;
    String Metascore;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDVD() {
        return DVD;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    String imdbRating;
    String imdbVotes;
    String imdbID;
    String Type;
    String DVD;
    String BoxOffice;
    String Production;
    String Website;

    @Override

    public int describeContents() {

        return 0;

    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(Title);//写出name

        dest.writeString(Year);//写出Year

        dest.writeString(Director);//写出Director

        dest.writeString(Writer);//写出Writer

        dest.writeString(Actors);//写出Actors

        dest.writeString(Language);//写出Language

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override

        public Film createFromParcel(Parcel source) {

            Film film = new Film();

            film.Title = source.readString();//读取name,顺序一致

            film.Year = source.readString();//读取age

            film.Director = source.readString();//读取name,顺序一致

            film.Writer = source.readString();//读取age

            film.Actors = source.readString();//读取name,顺序一致

            film.Language = source.readString();//读取age



            return film;

        }

        @Override

        public Film[] newArray(int size) {

            return new Film[size];

        }

    };

}