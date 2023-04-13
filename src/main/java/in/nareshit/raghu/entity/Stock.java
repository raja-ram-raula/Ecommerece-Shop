package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="stock_tab")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stock_id_col")
	private Long id;
	
	@Column(name="stock_qoh_col")
	private Long qoh;
	
	@Column(name="stock_sold_col")
	private Long sold;
	
	@OneToOne
	@JoinColumn(name="prod_id_fk_col")
	private Product product;
	
	@Transient
	private Long count;
}
