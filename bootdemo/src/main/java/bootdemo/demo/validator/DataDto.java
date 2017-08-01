package bootdemo.demo.validator;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DataDto implements Serializable {

	@NotNull(message="{form.addresses.NotNull.message}")
	@Size(min = 1, max = 100)
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}
