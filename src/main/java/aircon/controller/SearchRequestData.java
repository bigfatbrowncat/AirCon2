package aircon.controller;

public class SearchRequestData {
	private String customerNameSearchRequest;
	private String productManufacturerAndModelSearchRequest;
	private String targetAddressSearchRequest;
	private boolean searchNew;
	private boolean searchInspected;
	private boolean searchCompleted;
	private boolean searchCancelled;
	
	public String getCustomerNameSearchRequest() {
		return customerNameSearchRequest;
	}
	public void setCustomerNameSearchRequest(String customerNameSearchRequest) {
		this.customerNameSearchRequest = customerNameSearchRequest;
	}
	public String getProductManufacturerAndModelSearchRequest() {
		return productManufacturerAndModelSearchRequest;
	}
	public void setProductManufacturerAndModelSearchRequest(
			String productManufacturerAndModelSearchRequest) {
		this.productManufacturerAndModelSearchRequest = productManufacturerAndModelSearchRequest;
	}
	public String getTargetAddressSearchRequest() {
		return targetAddressSearchRequest;
	}
	public void setTargetAddressSearchRequest(String targetAddressSearchRequest) {
		this.targetAddressSearchRequest = targetAddressSearchRequest;
	}
	public boolean isSearchNew() {
		return searchNew;
	}
	public void setSearchNew(boolean searchNew) {
		this.searchNew = searchNew;
	}
	public boolean isSearchInspected() {
		return searchInspected;
	}
	public void setSearchInspected(boolean searchInspected) {
		this.searchInspected = searchInspected;
	}
	public boolean isSearchCompleted() {
		return searchCompleted;
	}
	public void setSearchCompleted(boolean searchCompleted) {
		this.searchCompleted = searchCompleted;
	}
	public boolean isSearchCancelled() {
		return searchCancelled;
	}
	public void setSearchCancelled(boolean searchCancelled) {
		this.searchCancelled = searchCancelled;
	}
}
