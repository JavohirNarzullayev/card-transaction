package uz.narzullaev.cardtrx.mapper;

import lombok.experimental.UtilityClass;
import uz.narzullaev.cardtrx.dto.user.UserResDto;
import uz.narzullaev.cardtrx.dto.user.UserRqDto;
import uz.narzullaev.cardtrx.entity.User;

@UtilityClass
public class UserMapper {


    public User toEntity(UserRqDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setBalance(dto.balance());
        return user;
    }

    public User toEntity(UserRqDto dto, User user) {
        user.setName(dto.name());
        user.setBalance(dto.balance());
        return user;
    }

    public UserResDto toResponse(User user) {
        return UserResDto.builder()
                .id(user.getId())
                .balance(user.getBalance())
                .name(user.getName())
                .build();
    }
}
