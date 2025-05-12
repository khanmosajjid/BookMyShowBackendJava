package com.mosajjid.bookmyshow.repositories;

import com.mosajjid.bookmyshow.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ss from ShowSeat  ss where ss.id IN :showIds")
    List<ShowSeat> findAllShowSeatAndLock(List<Long> showIds);
}
