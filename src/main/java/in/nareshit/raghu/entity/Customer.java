package in.nareshit.raghu.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Entity
@Table(name="customer_tab")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id_col")
	private Long id;
	
	@Column(name="customer_name_col")
	private String name;
	@Column(name="customer_email_col")
	private String email;
	
	@Column(name="customer_mob_col")
	private String mobile;
	
	@Column(name="customer_gen_col")
	private String gender;
	
	@Column(name="customer_dob_col")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id_fk_col")
	private List<Address> address; 
	
}
