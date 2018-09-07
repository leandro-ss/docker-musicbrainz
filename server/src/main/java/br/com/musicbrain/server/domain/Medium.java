package br.com.musicbrain.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="medium")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medium {

	@Id
	@SequenceGenerator(sequenceName = "medium_id_seq", name = "medium_seq")
	private Integer id;
	
	private Integer release;
    private Integer edits_pending;
    private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
	private Date last_updated;

	/**
	 * @return the name1
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name1 the name1 to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the release
	 */
	public Integer getRelease() {
		return release;
	}

	/**
	 * @param release the release to set
	 */
	public void setRelease(Integer release) {
		this.release = release;
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