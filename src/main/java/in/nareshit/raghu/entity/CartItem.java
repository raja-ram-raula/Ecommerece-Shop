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
@Table(name="cart_items_tab")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id_col")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="prod_id_fk_col")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cust_id_fk_col")
	private Customer customer;
	
	@Column(name="cart_qty_col")
	private Integer qty;
}
