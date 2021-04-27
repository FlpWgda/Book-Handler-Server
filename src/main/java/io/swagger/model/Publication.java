package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Genre;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Publication
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")

@Entity
@Table
public class Publication   {
  public Publication(){

  }

  public Publication(Long id, String title, String author, Integer releaseYear, @Valid List<Genre> genre, OffsetDateTime dateAdded, String photoUrls) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.releaseYear = releaseYear;
    this.genre = genre;
    this.dateAdded = dateAdded;
    this.photoUrls = photoUrls;
    this.numberOfRatings = 0;
    this.rating = 0.0f;
  }
  public Publication(Long id, String title, String author, Integer releaseYear, String photoUrls) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.releaseYear = releaseYear;
    this.photoUrls = photoUrls;
    this.numberOfRatings = 0;
    this.rating = 0.0f;
  }

  public static List<Publication> publications = new ArrayList<Publication>();

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id = null;

  @NotNull(message="required field")
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("releaseYear")
  private Integer releaseYear = null;

  @ManyToMany
  @JoinTable(
    joinColumns = { @JoinColumn(name = "publicationId")},
    inverseJoinColumns = { @JoinColumn(name = "genreName")}
  )
  @JsonProperty("genre")
  @Valid
  private List<Genre> genre = null;

  @JsonProperty("dateAdded")
  private OffsetDateTime dateAdded = null;

  @JsonProperty("numberOfRatings")
  private Integer numberOfRatings = null;

  public Integer getNumberOfRatings() {
    return numberOfRatings;
  }

  public void setNumberOfRatings(Integer numberOfRatings) {
    this.numberOfRatings = numberOfRatings;
  }

  @JsonProperty("rating")
  private Float rating = null;

  @JsonProperty("photoUrls")
  @Valid
  private String photoUrls = null;

  @JsonProperty("addedBy")
  @ManyToOne
  private User addedBy = null;

  public Publication id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Publication title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Publication author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   **/
  @Schema(description = "")

    public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Publication releaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
    return this;
  }

  /**
   * Get releaseYear
   * @return releaseYear
   **/
  @Schema(description = "")

    public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Publication genre(List<Genre> genre) {
    this.genre = genre;
    return this;
  }

  public Publication addGenreItem(Genre genreItem) {
    if (this.genre == null) {
      this.genre = new ArrayList<Genre>();
    }
    this.genre.add(genreItem);
    return this;
  }

  /**
   * Get genre
   * @return genre
   **/
  @Schema(description = "")
      @Valid
    public List<Genre> getGenre() {
    return genre;
  }

  public void setGenre(List<Genre> genre) {
    this.genre = genre;
  }

  public Publication dateAdded(OffsetDateTime dateAdded) {
    this.dateAdded = dateAdded;
    return this;
  }

  /**
   * Get dateAdded
   * @return dateAdded
   **/
  @Schema(description = "")

    @Valid
    public OffsetDateTime getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(OffsetDateTime dateAdded) {
    this.dateAdded = dateAdded;
  }

  public Publication rating(Float rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Get rating
   * @return rating
   **/
  @Schema(description = "")

    public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public Publication photoUrls(String photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  /**
   * Get photoUrls
   * @return photoUrls
   **/
  @Schema(description = "")

    public String getPhotoUrls() {
    return photoUrls;
  }

  public void setPhotoUrls(String photoUrls) {
    this.photoUrls = photoUrls;
  }

  public Publication addedBy(User addedBy) {
    this.addedBy = addedBy;
    return this;
  }

  /**
   * Get addedBy
   * @return addedBy
   **/
  @Schema(description = "")

    @Valid
    public User getAddedBy() {
    return addedBy;
  }

  public void setAddedBy(User addedBy) {
    this.addedBy = addedBy;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Publication publication = (Publication) o;
    return Objects.equals(this.id, publication.id) &&
        Objects.equals(this.title, publication.title) &&
        Objects.equals(this.author, publication.author) &&
        Objects.equals(this.releaseYear, publication.releaseYear) &&
        Objects.equals(this.genre, publication.genre) &&
        Objects.equals(this.dateAdded, publication.dateAdded) &&
        Objects.equals(this.rating, publication.rating) &&
        Objects.equals(this.photoUrls, publication.photoUrls) &&
        Objects.equals(this.addedBy, publication.addedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, releaseYear, genre, dateAdded, rating, photoUrls, addedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Publication {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
    sb.append("    dateAdded: ").append(toIndentedString(dateAdded)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    addedBy: ").append(toIndentedString(addedBy)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
