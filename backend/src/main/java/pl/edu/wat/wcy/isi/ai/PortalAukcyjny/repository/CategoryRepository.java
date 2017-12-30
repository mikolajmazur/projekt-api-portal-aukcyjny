package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.isi.ai.PortalAukcyjny.entity.Category;

import java.util.Collection;

import static javax.persistence.criteria.JoinType.INNER;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query("select distinct e FROM Category e LEFT JOIN e.children m WHERE e.parent is null")
    Collection<Category> findAll();
}
