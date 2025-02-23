package uz.narzullaev.cardtrx.resource.rest.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.narzullaev.cardtrx.service.UserService;
import uz.narzullaev.cardtrx.dto.BaseResponse;
import uz.narzullaev.cardtrx.dto.user.UserResDto;
import uz.narzullaev.cardtrx.dto.user.UserRqDto;
import uz.narzullaev.cardtrx.entity.User;
import uz.narzullaev.cardtrx.mapper.UserMapper;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "Методы для управления пользователями")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Создания пользователя")
    public BaseResponse<UserResDto> createUser(@RequestBody UserRqDto dto) {
        var save = userService.save(dto);
        return BaseResponse.successResponse(UserMapper.toResponse(save));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновит пользователя")
    public BaseResponse<UserResDto> updateUser(@PathVariable Long id,
                                               @RequestBody UserRqDto dto
    ) {
        User user = userService.updateUser(id, dto);
        return BaseResponse.successResponse(UserMapper.toResponse(user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация по id пользователя")
    public BaseResponse<UserResDto> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return BaseResponse.successResponse(UserMapper.toResponse(user));
    }


}
