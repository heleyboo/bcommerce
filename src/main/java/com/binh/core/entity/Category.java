package com.binh.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "slug")
	private String slug;

	@Column(name = "position")
	private int position;

	@Column(name = "level")
	private int level;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "code", nullable = true)
    private Category parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<Category> children;

	@Column(name = "is_visible", insertable = false)
	private Boolean isVisible;

	@Column(name = "is_enable", insertable = false)
	private Boolean isEnable;

	@Column(name = "description")
	private String description;
}