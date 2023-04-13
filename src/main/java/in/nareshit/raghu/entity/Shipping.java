package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Entity
@Table(name = "shipping_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name="ship_type_col")
	private String shipType;
	
	@Column(name="ship_code_col")
	private String shipCode;
	
	@Column(name="ship_name_col")
	private String shipName;
	
	@Column(name="ship_cost_col")
	private String shipCost;
	
	@Column(name="ship_weight_col")
	private String shipWeight;
	
	@Column(name="ship_weight_type_col")
	private String shipWeightType;
	
	@Column(name="ship_note_col")
	private String note;
	
	
	
}
