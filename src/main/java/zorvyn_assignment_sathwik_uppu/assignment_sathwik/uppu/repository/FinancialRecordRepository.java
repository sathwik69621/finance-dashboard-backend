package zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zorvyn_assignment_sathwik_uppu.assignment_sathwik.uppu.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'INCOME'")
Double getTotalIncome();

@Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = 'EXPENSE'")
Double getTotalExpense();
    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByCategory(String category);

    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);
}