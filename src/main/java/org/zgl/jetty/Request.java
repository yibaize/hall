package org.zgl.jetty;

public class Request {
	private short id;
	/**
	 * 数据
	 */
	private Msg data;

	public Request() {
	}

	public Request(short id, Msg data) {
		this.id = id;
		this.data = data;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public Msg getData() {
		return data;
	}

	public void setData(Msg data) {
		this.data = data;
	}
}