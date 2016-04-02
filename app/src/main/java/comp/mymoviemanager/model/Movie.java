package comp.mymoviemanager.model;

/**
 * Author: lupcorrea
 * Day: 01/04/16
 */

/* TODO: Edit the release methods so it only deals with years. */
/* TODO: Add the lists for actors and producers, if possible. */
public class Movie {
    private String name;
    private String release;
    private String category;
    private String popularity;
    private String country;
    private String sinopsis;

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
