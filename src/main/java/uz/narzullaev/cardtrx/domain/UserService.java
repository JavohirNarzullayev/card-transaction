package uz.narzullaev.cardtrx.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.narzullaev.cardtrx.dto.user.UserRqDto;
import uz.narzullaev.cardtrx.entity.User;
import uz.narzullaev.cardtrx.mapper.UserMapper;
import uz.narzullaev.cardtrx.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User save(UserRqDto dto) {
        var user = UserMapper.toEntity(dto);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserRqDto dto) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        var mergedUser = UserMapper.toEntity(dto, user);
        return userRepository.save(mergedUser);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
