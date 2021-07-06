package practice.oracleminipro;

// 사진파일을 그대로 작성해서 오류 확인하기

public class PhoneVo {

	private int personID;
	private String name;
	private String hp;
	private String company;

	public PhoneVo() {
	}

	public PhoneVo(int e) {
		this.personID = e;
	}

	public PhoneVo(String a, String b, String c) {
		this.name = a;
		this.hp = b;
		this.company = c;
	}

	public PhoneVo(int i, String n, String h, String c) {
		this.personID = i;
		this.name = n;
		this.hp = h;
		this.company = c;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int e) {
		this.personID = e;
	}

	public String getName() {
		return name;
	}

	public void setName(String e) {
		this.name = e;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String e) {
		this.hp = e;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String e) {
		this.company = e;
	}

	public String toString() {
		return "PhoneVo [personID= " + personID + ", name= " + name + ", hp= " + hp + ", company= " + company + "]";
	}

}
