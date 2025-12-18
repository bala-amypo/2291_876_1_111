package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompatibilityScoreRecordRepository extends JpaRepository<Location,Long> {

}
package com.example.demo.repository;


import com.example.demo.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}