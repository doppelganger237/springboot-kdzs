package com.zhang.kdzs.utils;

import com.zhang.kdzs.common.dto.Tree;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: 挺垃圾的 准备重写
* @Param: 
* @return: 
* @date: 2019/8/17
 */
public class TreeUtils {
	
	
	public static <T> List<Tree<T>> build(List<Tree<T>> nodes) {
		if (nodes == null) {
			return null;
		}

		List<Tree<T>> tree = new ArrayList<>();
		nodes.forEach(children -> {
			Long pid = children.getParentId();
			if (pid == null || pid.equals(0L)) {
				// 是父节点
				tree.add(children);
				return;
			}
			for (Tree<T> parent : nodes) {
				Long id = parent.getId();
				if (id != null && id.equals(pid)) {
					// 说明是该节点是children的父节点
					children.setHasParent(true);
					parent.setHasChildren(true);
					parent.getChildren().add(children);
					return;
				}
			}
		});
		return tree;
	}
}
