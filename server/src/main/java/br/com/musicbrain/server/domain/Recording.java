package br.com.musicbrain.server.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="recording")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recording {

	@Id
	@SequenceGenerator(sequenceName = "recording_id_seq", name = "recording_seq")
	private Integer id;

	@OneToMany(mappedBy="recording", fetch=FetchType.LAZY)
    private Set<Track> trackSet = new HashSet<>();
	
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID gid;
    private String name;
    private Integer artist_credit;
	
    private String comment;
    private Integer length;
    private Integer edits_pending;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
	private Date last_updated;
	
	private Boolean video;

	/**
	 * @return the set
	 */
	public Set<Track> getTrackSet() {
		return trackSet;
	}

	/**
	 * @param last_updated the last_updated to set
	 */
	public void setTrackSet(Set<Track> trackSet) {
		this.trackSet = trackSet;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @return the video
	 */
	public Boolean getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(Boolean video) {
		this.video = video;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the gid
	 */
	public UUID getGid() {
		return gid;
	}
	/**
	 * @param gid the gid to set
	 */
	public void setGid(UUID gid) {
		this.gid = gid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the artist_credit
	 */
	public Integer getArtist_credit() {
		return artist_credit;
	}
	/**
	 * @param artist_credit the artist_credit to set
	 */
	public void setArtist_credit(Integer artist_credit) {
		this.artist_credit = artist_credit;
	}
	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * @return the edits_pending
	 */
	public Integer getEdits_pending() {
		return edits_pending;
	}
	/**
	 * @param edits_pending the edits_pending to set
	 */
	public void setEdits_pending(Integer edits_pending) {
		this.edits_pending = edits_pending;
	}
	/**
	 * @return the last_updated
	 */
	public Date getLast_updated() {
		return last_updated;
	}
	/**
	 * @param last_updated the last_updated to set
	 */
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

    @Override
    public String toString() {
        return String.format("Message [id=%s, name= %s]", this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Track )
            return this.toString().equals(obj.toString());
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}