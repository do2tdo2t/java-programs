package companychat.vo;

public class RoomVO {
	public EmployeeVO user;
	public EmployeeVO recv;
	
	public RoomVO (EmployeeVO user, EmployeeVO recv) {
		this.user = user;
		this.recv = recv;
	}
	
	
}
