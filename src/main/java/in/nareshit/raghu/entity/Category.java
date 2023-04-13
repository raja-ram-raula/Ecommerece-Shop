package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Entity
@Table(name = "category_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="cat_name_col")
	private String name;
	
	@Column(name="cat_alias_col")
	private String alias;

	@Column(name="cat_status_col")
	private String status;

	@Column(name="cat_note_col")
	private String note;
	
	//---INTEGRATIONS----
	@ManyToOne
	@JoinColumn(name="cat_type_fk_col")
	private CategoryType categoryType;
}
