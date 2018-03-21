package com.shenkar.ir.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "terms")
@DynamicUpdate
@DynamicInsert
public class Term implements IEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private String term;
	
	@Formula("0")
	transient private Integer numberOfDocs;

	@Formula("0")
	transient private Integer hits;

	public Term() {
		super();
	}

	public Term(String term, Integer numberOfDocs, Integer hits) {
		super();
		this.term = term;
		this.numberOfDocs = numberOfDocs;
		this.hits = hits;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Integer getNumberOfDocs() {
		return numberOfDocs;
	}

	public void setNumberOfDocs(Integer numberOfDocs) {
		this.numberOfDocs = numberOfDocs;
	}

	public int getFrequency() {
		return hits;
	}

	public void setFrequency(Integer frequency) {
		this.hits = frequency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Term [term=" + term + ", numberOfDocs=" + numberOfDocs + ", frequency=" + hits + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Term other = (Term) obj;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}
}
