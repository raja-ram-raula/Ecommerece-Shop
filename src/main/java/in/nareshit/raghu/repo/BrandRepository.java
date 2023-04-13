package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	@Query("SELECT id, name FROM Brand")
	List<Object[]> getBrandIdAndName();

	@Query("SELECT id, imageLink FROM Brand")
	List<Object[]> getBrandIdAndImage();
}
