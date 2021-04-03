package com.gigs.maher1957.Models.Order;

import com.google.gson.annotations.SerializedName;

public class Order{

	@SerializedName("tax_amount")
	private String taxAmount;

	@SerializedName("cancelled_at")
	private Object cancelledAt;

	@SerializedName("note")
	private String note;

	@SerializedName("payment_url")
	private String paymentUrl;

	@SerializedName("code")
	private String code;

	@SerializedName("base_total_price")
	private String baseTotalPrice;

	@SerializedName("discount_amount")
	private String discountAmount;

	@SerializedName("payment_token")
	private String paymentToken;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("payment_due")
	private String paymentDue;

	@SerializedName("customer_province_id")
	private String customerProvinceId;

	@SerializedName("tax_percent")
	private String taxPercent;

	@SerializedName("customer_last_name")
	private String customerLastName;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("customer_postcode")
	private int customerPostcode;

	@SerializedName("approved_at")
	private Object approvedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("grand_total")
	private String grandTotal;

	@SerializedName("shipping_service_name")
	private String shippingServiceName;

	@SerializedName("read")
	private String read;

	@SerializedName("cancelled_by")
	private Object cancelledBy;

	@SerializedName("cancellation_note")
	private Object cancellationNote;

	@SerializedName("shipping_cost")
	private String shippingCost;

	@SerializedName("customer_city_id")
	private String customerCityId;

	@SerializedName("shipping_courier")
	private String shippingCourier;

	@SerializedName("payment_status")
	private String paymentStatus;

	@SerializedName("customer_first_name")
	private String customerFirstName;

	@SerializedName("customer_phone")
	private String customerPhone;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("customer_address1")
	private String customerAddress1;

	@SerializedName("order_date")
	private String orderDate;

	@SerializedName("customer_address2")
	private String customerAddress2;

	@SerializedName("customer_full_name")
	private String customerFullName;

	@SerializedName("approved_by")
	private Object approvedBy;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("customer_email")
	private String customerEmail;

	@SerializedName("discount_percent")
	private String discountPercent;

	@SerializedName("status")
	private String status;

	public void setTaxAmount(String taxAmount){
		this.taxAmount = taxAmount;
	}

	public String getTaxAmount(){
		return taxAmount;
	}

	public void setCancelledAt(Object cancelledAt){
		this.cancelledAt = cancelledAt;
	}

	public Object getCancelledAt(){
		return cancelledAt;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setPaymentUrl(String paymentUrl){
		this.paymentUrl = paymentUrl;
	}

	public String getPaymentUrl(){
		return paymentUrl;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setBaseTotalPrice(String baseTotalPrice){
		this.baseTotalPrice = baseTotalPrice;
	}

	public String getBaseTotalPrice(){
		return baseTotalPrice;
	}

	public void setDiscountAmount(String discountAmount){
		this.discountAmount = discountAmount;
	}

	public String getDiscountAmount(){
		return discountAmount;
	}

	public void setPaymentToken(String paymentToken){
		this.paymentToken = paymentToken;
	}

	public String getPaymentToken(){
		return paymentToken;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPaymentDue(String paymentDue){
		this.paymentDue = paymentDue;
	}

	public String getPaymentDue(){
		return paymentDue;
	}

	public void setCustomerProvinceId(String customerProvinceId){
		this.customerProvinceId = customerProvinceId;
	}

	public String getCustomerProvinceId(){
		return customerProvinceId;
	}

	public void setTaxPercent(String taxPercent){
		this.taxPercent = taxPercent;
	}

	public String getTaxPercent(){
		return taxPercent;
	}

	public void setCustomerLastName(String customerLastName){
		this.customerLastName = customerLastName;
	}

	public String getCustomerLastName(){
		return customerLastName;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCustomerPostcode(int customerPostcode){
		this.customerPostcode = customerPostcode;
	}

	public int getCustomerPostcode(){
		return customerPostcode;
	}

	public void setApprovedAt(Object approvedAt){
		this.approvedAt = approvedAt;
	}

	public Object getApprovedAt(){
		return approvedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setGrandTotal(String grandTotal){
		this.grandTotal = grandTotal;
	}

	public String getGrandTotal(){
		return grandTotal;
	}

	public void setShippingServiceName(String shippingServiceName){
		this.shippingServiceName = shippingServiceName;
	}

	public String getShippingServiceName(){
		return shippingServiceName;
	}

	public void setRead(String read){
		this.read = read;
	}

	public String getRead(){
		return read;
	}

	public void setCancelledBy(Object cancelledBy){
		this.cancelledBy = cancelledBy;
	}

	public Object getCancelledBy(){
		return cancelledBy;
	}

	public void setCancellationNote(Object cancellationNote){
		this.cancellationNote = cancellationNote;
	}

	public Object getCancellationNote(){
		return cancellationNote;
	}

	public void setShippingCost(String shippingCost){
		this.shippingCost = shippingCost;
	}

	public String getShippingCost(){
		return shippingCost;
	}

	public void setCustomerCityId(String customerCityId){
		this.customerCityId = customerCityId;
	}

	public String getCustomerCityId(){
		return customerCityId;
	}

	public void setShippingCourier(String shippingCourier){
		this.shippingCourier = shippingCourier;
	}

	public String getShippingCourier(){
		return shippingCourier;
	}

	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}

	public void setCustomerFirstName(String customerFirstName){
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerFirstName(){
		return customerFirstName;
	}

	public void setCustomerPhone(String customerPhone){
		this.customerPhone = customerPhone;
	}

	public String getCustomerPhone(){
		return customerPhone;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setCustomerAddress1(String customerAddress1){
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress1(){
		return customerAddress1;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public void setCustomerAddress2(String customerAddress2){
		this.customerAddress2 = customerAddress2;
	}

	public String getCustomerAddress2(){
		return customerAddress2;
	}

	public void setCustomerFullName(String customerFullName){
		this.customerFullName = customerFullName;
	}

	public String getCustomerFullName(){
		return customerFullName;
	}

	public void setApprovedBy(Object approvedBy){
		this.approvedBy = approvedBy;
	}

	public Object getApprovedBy(){
		return approvedBy;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCustomerEmail(String customerEmail){
		this.customerEmail = customerEmail;
	}

	public String getCustomerEmail(){
		return customerEmail;
	}

	public void setDiscountPercent(String discountPercent){
		this.discountPercent = discountPercent;
	}

	public String getDiscountPercent(){
		return discountPercent;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}