package uz.narzullaev.cardtrx.resource.rest.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transaction")
@Tag(name = "Методы для трансакции")
public class TransactionResource {
}
