package com.wagawin.crazyfamilies.repository;

import com.wagawin.crazyfamilies.model.ParentSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParentSummaryRepository extends CrudRepository<ParentSummary, Long>{

    @Query(value = "select count(*) as person_count, 0 as child_count from person p left join person_children pc on p.id = pc.person_id where pc.person_id is null union select count(*) as person_count, sums.child_count as child_count from (select person_id, count(*) as child_count from person_children group by person_id) sums group by sums.child_count order by child_count ASC", nativeQuery = true)
    List<ParentSummary> getStatisticsOfParentChildCount();

}
