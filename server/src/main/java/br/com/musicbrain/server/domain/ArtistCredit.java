package br.com.musicbrain.server.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="artist_credit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArtistCredit {

	@Id
	@GeneratedValue(generator="artist_credit_seq")
	@SequenceGenerator(sequenceName = "artist_credit_id_seq", name = "artist_credit_seq")
	private Integer id;

	@OneToMany(mappedBy="artistCredit", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Recording> recordingSet = new HashSet<>();
	
	private String name;
		
	private Integer refCount;
	
	@Column(columnDefinition = "SMALLINT")
	private Short artistCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
	private Date created;

	/**
	 * @return the name1
	 */
	public Set<Recording> getRecordingSet() {
		return recordingSet;
	}

	/**
	 * @param artistCount the artistCount to set
	 */
	public void setRecordingSet(Set<Recording> recordingSet) {
		this.recordingSet = recordingSet;
	}
	
	/**
	 * @return the name1
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the artistCount
	 */
	public Short getArtistCount() {
		return artistCount;
	}

	/**
	 * @param artistCount the artistCount to set
	 */
	public void setArtistCount(Short artistCount) {
		this.artistCount = artistCount;
	}

	/**
	 * @return the refCount
	 */
	public Integer getRefCount() {
		return refCount;
	}

	/**
	 * @param refCount the refCount to set
	 */
	public void setRefCount(Integer refCount) {
		this.refCount = refCount;
	}

	/**
	 * @param name1 the name1 to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the id
	 */
	public Date getCreated() {
		return created;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setCreated(Date created) {
		this.created = created;
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