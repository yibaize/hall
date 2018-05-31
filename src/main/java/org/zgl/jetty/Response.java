package org.zgl.jetty;

public class Response {
	private short id;

	/**
	 * 数据
	 */
	private byte[] data;

	public Response() {
	}

	public Response(short id, byte[] data) {
		this.id = id;
		this.data = data;
	}

	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] dATA) {
		data = dATA;
	}
	public int getDataLength(){
		if(data != null){
			return data.length;
		}
		return 0;
	}
}