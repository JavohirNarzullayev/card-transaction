package uz.narzullaev.cardtrx.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.narzullaev.cardtrx.entity.Transaction;
import uz.narzullaev.cardtrx.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>, JpaSpecificationExecutor<Transaction> {


}