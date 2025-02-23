package uz.narzullaev.cardtrx.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.narzullaev.cardtrx.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}