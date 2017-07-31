package bootdemo.demo.validator;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Form {
	    @NotNull
	    private Boolean hasComputer;

	    @NotNull
	    private Boolean hasMobileDevices;

	    @NotNull
	    @Size(min = 1, max = 3) // (1)
	    @Valid
	    private List<DataDto> addresses;



	    public Boolean getHasComputer() {
			return hasComputer;
		}



		public void setHasComputer(Boolean hasComputer) {
			this.hasComputer = hasComputer;
		}



		public Boolean getHasMobileDevices() {
			return hasMobileDevices;
		}



		public void setHasMobileDevices(Boolean hasMobileDevices) {
			this.hasMobileDevices = hasMobileDevices;
		}



		public List<DataDto> getAddresses() {
			return addresses;
		}



		public void setAddresses(List<DataDto> addresses) {
			this.addresses = addresses;
		}



		public Form() {
	    }



}
