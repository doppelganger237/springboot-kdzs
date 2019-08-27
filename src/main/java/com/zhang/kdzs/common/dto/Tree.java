package com.zhang.kdzs.common.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Tree<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755354209751063316L;

	private Long id; // 节点ID

	private Long parentId; // 父节点ID

	private Boolean hasChildren; // 是否有子节点

	private Boolean hasParent; // 是否有父节点

	private String name; // 节点名称

	private String url; // 节点URL

	private String icon; // 图标

	private List<Tree<T>> children = new ArrayList<>(); // 子节点信息
}
