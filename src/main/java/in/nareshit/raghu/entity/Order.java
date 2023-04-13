package in.nareshit.raghu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_tab")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id_col")
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="order_id_fk_col")
	private List<OrderItem> orderItems;
	
	@ManyToOne
	@JoinColumn(name="cust_id_fk_col")
	private Customer customer;
	
	@Column(name="order_status_col")
	private String status;
	
	@Column(name="order_grand_total_col")
	private Double grandTotal;
	
	@ManyToOne
	@JoinColumn(name="ord_coupon_id_fk_col")
	private Coupon coupon;
	
	@Column(name="order_coupon_col")
	private Double couponAmt;
	
	@Column(name="order_billing_amt_col")
	private Double billingAmt;
}
