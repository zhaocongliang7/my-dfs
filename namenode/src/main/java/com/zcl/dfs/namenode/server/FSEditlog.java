package com.zcl.dfs.namenode.server;

import java.util.LinkedList;

public class FSEditlog {

	private long txidSeq = 0L;
	private DoubleBuffer editLogBuffer = new DoubleBuffer();
	private volatile Boolean isSyncRunning = false;
	private volatile Boolean isWaitSync = false;
	private volatile Long syncMaxTxid = 0L;
	private ThreadLocal<Long> localTxid = new ThreadLocal<Long>();
	

	public void logEdit(String content) {
		synchronized(this) {
			txidSeq++;
			long txid = txidSeq;
			localTxid.set(txid);
			EditLog log = new EditLog(txid, content);
			editLogBuffer.write(log);  
		}
		
		logSync();
	}

	private void logSync() {
		synchronized(this) {
			if(isSyncRunning) {
				long txid = localTxid.get();
				if(txid <= syncMaxTxid) {
					return;
				}
				if(isWaitSync) {
					return;
				}
				isWaitSync = true;
				while(isSyncRunning) {
					try {
						wait(2000);
					} catch (Exception e) {
						e.printStackTrace();  
					}
				}
				isWaitSync = false;
			}
			
			editLogBuffer.setReadyToSync();
			syncMaxTxid = editLogBuffer.getSyncMaxTxid();
			isSyncRunning = true;
		}
		editLogBuffer.flush();  
		
		synchronized(this) {
			isSyncRunning = false;
			notifyAll();
		}
	}
	class EditLog {
	
		long txid;
		String content;
		
		public EditLog(long txid, String content) {
			this.txid = txid;
			this.content = content;
		}
		
	}

	class DoubleBuffer {
		LinkedList<EditLog> currentBuffer = new LinkedList<EditLog>();
		LinkedList<EditLog> syncBuffer = new LinkedList<EditLog>();
		public void write(EditLog log) {
			currentBuffer.add(log);
		}
		public void setReadyToSync() {
			LinkedList<EditLog> tmp = currentBuffer;
			currentBuffer = syncBuffer;
			syncBuffer = tmp;
		}
		public Long getSyncMaxTxid() {
			return syncBuffer.getLast().txid;
		}
		public void flush() {
			for(EditLog log : syncBuffer) {
				System.out.println("将edit log写入磁盘文件中：" + log); 
			}
			syncBuffer.clear();  
		}
		
	}
	
}
