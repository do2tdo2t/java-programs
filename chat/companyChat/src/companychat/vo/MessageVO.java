package companychat.vo;


import companychat.util.*;

/*file에 쓰여질 message*/
public class MessageVO{
	private int type = Constant.MSG;
	private int sender; //id
	private int receiver; //id
	private String content;
	private String writeDate = DateTime.getDatetime();
	
	public MessageVO() {
		
	}
	
	public MessageVO(int sender,int receiver ,String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.writeDate = DateTime.getDatetime();
	}
	
	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "MessageVO [type=" + type + ", sender=" + sender + ", receiver=" + receiver + ", content=" + content
				+ ", writeDate=" + writeDate + "]";
	}
	
	
	

}
