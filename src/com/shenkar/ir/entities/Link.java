package com.shenkar.ir.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "links")
@DynamicUpdate
@DynamicInsert
public class Link implements IEntity, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "term_val", referencedColumnName = "term")
	@Fetch(FetchMode.SELECT)
	@OrderBy("term ASC")
	private Term term;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "doc_id", referencedColumnName = "id")
	@Fetch(FetchMode.SELECT)
	private Document document;
	
	@Column(nullable = false)
	private Integer hits = 0;

	public Link() {
		super();
	}

	public Link(Term term, Document document, Integer hits) {
		super();
		this.term = term;
		this.document = document;
		this.hits = hits;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Link [term=" + term + ", document=" + document + ", hits=" + hits + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + hits;
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (hits != other.hits)
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}
}
