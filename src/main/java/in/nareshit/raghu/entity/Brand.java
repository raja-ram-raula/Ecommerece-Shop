package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="brandtab")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="brnd_id_col")
	private Long id;

	@Column(name="brnd_name_col")
	private String name;
	
	@Column(name="brnd_code_col")
	private String code;
	
	@Column(name="brnd_tagline_col")
	private String tagLine;
	
	@Column(name="brnd_image_col")
	private String imageLink;
	
	@Column(name="brnd_note_col")
	private String note;
}
