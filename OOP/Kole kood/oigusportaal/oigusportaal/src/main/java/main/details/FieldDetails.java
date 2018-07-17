package main.details;

public class FieldDetails {
	
	int fieldId;
	boolean add;
	boolean delete;
	
	public FieldDetails(int fieldId){
		this.fieldId = fieldId;
	}
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldid) {
		this.fieldId = fieldid;
	}
	public boolean isAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

}
