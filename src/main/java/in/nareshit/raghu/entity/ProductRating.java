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
@Table(name="product_rating_tab")
public class ProductRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_rate_id_col")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="prod_id_fk_col")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cust_id_fk_col")
	private Customer customer;
	
	@Column(name="prod_star_col")
	private int starVal;
	
	@Column(name="prod_review_col")
	private String review;
}
