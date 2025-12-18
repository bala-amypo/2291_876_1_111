package com.example.demo.repository;

public class CompatibilityScoreRecordRepository {

}
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}