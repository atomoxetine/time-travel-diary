package logemi.ttdiary.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

import javax.annotation.Nullable;

public class DiaryEntry implements Serializable { 
  private String content;
  private String title;
  private Date creationTime;
  private Date referenceTime;
  private TreeSet<String> tags;

  public DiaryEntry(String content, @Nullable String title, Date referenceTime, @Nullable TreeSet<String> tags) {
    this.content = content;
    if (title != null) this.title = title;
    else this.title = "";
    this.referenceTime = referenceTime;
    if (tags != null) this.tags = tags;
    else tags = new TreeSet<String>();

    this.creationTime = new Date();
  }

  public String getContent() {
    return content;
  }

  public String getTitle() {
    return title;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public Date getReferenceTime() {
    return referenceTime;
  }

  public Collection<String> getTags() {
    return tags;
  }

  public boolean containsTag(String tag) {
    return tags.contains(tag);
  }

  public boolean addTag(String tag) {
    return tags.add(tag);
  }

  public boolean removeTag(String tag) {
    return tags.remove(tag);
  }

}
