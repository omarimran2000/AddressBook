package book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long>{
    List<BuddyInfo> findByName(@Param("name") String name);
}
