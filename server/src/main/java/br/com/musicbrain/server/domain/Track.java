package br.com.musicbrain.server.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="track")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Track {

	@Id
	@SequenceGenerator(sequenceName = "track_id_seq", name = "track_seq")
	private Integer id;
	
	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID gid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="recording", nullable=false)
	private Recording recording;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="medium", nullable=false)
	private Medium medium;

    private Integer position;
    private String number;
    private String name;
    private Integer artist_credit;
    private Integer length;
    private Integer edits_pending;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
	
    private Date last_updated;
	private Boolean is_data_track;

	/**
	 * @return the is_data_track
	 */
	public Boolean getIs_data_track() {
		return is_data_track;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
	 * @return the recording
	 */
	public Recording getRecording() {
		return recording;
	}
	/**
	 * @param recording the recording to set
	 */
	public void setRecording(Recording recording) {
		this.recording = recording;
	}
	/**
	 * @return the medium
	 */
	public Medium getMedium() {
		return medium;
	}
	/**
	 * @param medium the medium to set
	 */
	public void setMedium(Medium medium) {
		this.medium = medium;
	}
	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
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
	/**
	 * @param is_data_track the is_data_track to set
	 */
	public void setIs_data_track(Boolean is_data_track) {
		this.is_data_track = is_data_track;
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