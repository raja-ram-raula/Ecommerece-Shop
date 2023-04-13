package in.nareshit.raghu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="track_order_tab")
public class TrackOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="track_id_col")
	private Long id;
	
	@Column(name="track_loca_col")
	private String location;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="track_date_col")
	private Date dte;
	
	@Column(name="track_desc_col")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="order_id_fk_col")
	private Order order;
}
