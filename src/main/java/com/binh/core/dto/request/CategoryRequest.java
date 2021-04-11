package com.binh.core.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
	@NotEmpty
	private String code;
	@NotEmpty
	private String name;
	private String slug;
	private int position;
    private String parent;
	private Boolean isVisible;
	private Boolean isEnable;
	private String description;
}
