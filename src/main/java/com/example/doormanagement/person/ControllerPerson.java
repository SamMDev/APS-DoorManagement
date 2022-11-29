package com.example.doormanagement.person;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.person.dto.response.LazyDataPerson;
import com.example.doormanagement.person.dto.response.PersonDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ControllerPerson {

    private final ServicePerson servicePerson;

    @GetMapping("/{id}")
    @Operation(description = "Gets person detail by id")
    public PersonDetailDto getDetail(@PathVariable("id") Long id) {
        return this.servicePerson.getDetail(id);
    }

    @PostMapping("/load")
    @Operation(description = "Lazy load person rows for lazy data table")
    public LazyData<LazyDataPerson> load(@RequestBody LazyCriteria criteria) {
        return this.servicePerson.load(criteria);
    }

}
