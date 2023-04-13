package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="order_item_tab")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_item_id_col")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="prod_id_fk_col")
	private Product product;
	
	@Column(name="order_item_qty_col")
	private Integer qty;
	
	@Column(name="order_item_discount_amt_col")
	private Double discount;
	
	@Column(name="order_item_amt_col")
	private Double lineAmount;
}
