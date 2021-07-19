package com.manav.startapp.services;

public class TransferFund {
	private Long beneficiary_acc_number;
	private float transfer_amount ;
	public Long getBeneficiary_acc_number() {
		return beneficiary_acc_number;
	}
	public void setBeneficiary_acc_number(Long beneficiary_acc_number) {
		this.beneficiary_acc_number = beneficiary_acc_number;
	}
	public float getTransfer_amount() {
		return transfer_amount;
	}
	public void setTransfer_amount(float transfer_amount) {
		this.transfer_amount = transfer_amount;
	}


}
