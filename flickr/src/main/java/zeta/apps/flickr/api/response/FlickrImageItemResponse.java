package zeta.apps.flickr.api.response;

import com.google.gson.annotations.SerializedName;

public class FlickrImageItemResponse {

    public final String title;
    public final String link;
    @SerializedName("date_taken")
    public final String dateTaken;
    public final String description;
    public final String published;
    public final String author;
    @SerializedName("author_id")
    public final String authorId;
    public final String tags;
    public final FlickrMediaResponse media;

    public FlickrImageItemResponse(String title, String link, String dateTaken,
                                   String description, String published,
                                   FlickrMediaResponse media,
                                   String author, String authorId, String tags) {
        this.title = title;
        this.link = link;
        this.media = media;
        this.dateTaken = dateTaken;
        this.description = description;
        this.published = published;
        this.author = author;
        this.authorId = authorId;
        this.tags = tags;
    }
}
