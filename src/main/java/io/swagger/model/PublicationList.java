package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Publication;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PublicationList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-25T09:56:25.993Z[GMT]")

@Entity
@Table
public class PublicationList   {

  public PublicationList(){}

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("createdBy")
  @ManyToOne
  private User createdBy = null;

  @JsonProperty("publications")
  @Valid
  @ManyToMany
  private List<Publication> publications = null;

  @JsonProperty("dateAdded")
  private OffsetDateTime dateAdded = null;

  public PublicationList id(Long id) {
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

  public PublicationList name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PublicationList createdBy(User createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  /**
   * Get createdBy
   * @return createdBy
   **/
  @Schema(description = "")

    @Valid
    public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public PublicationList publications(List<Publication> publications) {
    this.publications = publications;
    return this;
  }

  public PublicationList addPublicationsItem(Publication publicationsItem) {
    if (this.publications == null) {
      this.publications = new ArrayList<Publication>();
    }
    this.publications.add(publicationsItem);
    return this;
  }

  /**
   * Get publications
   * @return publications
   **/
  @Schema(description = "")
      @Valid
    public List<Publication> getPublications() {
    return publications;
  }

  public void setPublications(List<Publication> publications) {
    this.publications = publications;
  }

  public PublicationList dateAdded(OffsetDateTime dateAdded) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicationList publicationList = (PublicationList) o;
    return Objects.equals(this.id, publicationList.id) &&
        Objects.equals(this.name, publicationList.name) &&
        Objects.equals(this.createdBy, publicationList.createdBy) &&
        Objects.equals(this.publications, publicationList.publications) &&
        Objects.equals(this.dateAdded, publicationList.dateAdded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createdBy, publications, dateAdded);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublicationList {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    publications: ").append(toIndentedString(publications)).append("\n");
    sb.append("    dateAdded: ").append(toIndentedString(dateAdded)).append("\n");
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
