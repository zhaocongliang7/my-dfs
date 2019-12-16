package com.zcl.dfs.namenode.server;

import java.util.LinkedList;
import java.util.List;

public class FSDirectory {

	private INodeDirectory dirTree;
	
	public FSDirectory() {
		this.dirTree = new INodeDirectory("/");  
	}

	public void mkdir(String path) {
		synchronized(dirTree) {
			String[] pathes = path.split("/");
			INodeDirectory parent = dirTree;
			
			for(String splitedPath : pathes) {
				if(splitedPath.trim().equals("")) {
					continue;
				}
				
				INodeDirectory dir = findDirectory(parent, splitedPath);
				if(dir != null) {
					parent = dir;
					continue;
				}
				
				INodeDirectory child = new INodeDirectory(splitedPath); 
				parent.addChild(child);  
			}
		}
	}
	private INodeDirectory findDirectory(INodeDirectory dir, String path) {
		if(dir.getChildren().size() == 0) {
			return null;
		}
		
		INodeDirectory resultDir = null;
		
		for(INode child : dir.getChildren()) {
			if(child instanceof INodeDirectory) {
				INodeDirectory childDir = (INodeDirectory) child;
				
				if((childDir.getPath().equals(path))) {
					return childDir;
				} 
				
				resultDir = findDirectory(childDir, path);
				if(resultDir != null) {
					return resultDir;
				}
			}
		}
		
		return null;
	}

	private interface INode {
		
	}

	public static class INodeDirectory implements INode {
		
		private String path;
		private List<INode> children;
		
		public INodeDirectory(String path) {
			this.path = path;
			this.children = new LinkedList<INode>();
		}
		
		public void addChild(INode inode) {
			this.children.add(inode);
		}
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public List<INode> getChildren() {
			return children;
		}
		public void setChildren(List<INode> children) {
			this.children = children;
		}
		
	}

	public static class INodeFile implements INode {
		
		private String name;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
