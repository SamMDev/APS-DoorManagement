package com.example.doormanagement.chipcard;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.chipcard.dto.ChipCardDetail;
import com.example.doormanagement.chipcard.dto.LazyChipCardDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chip-card")
@RequiredArgsConstructor
public class ControllerChipCard {

    private final ServiceChipCard serviceChipCard;

    @PostMapping("/load")
    @Operation(description = "Lazy loads chip cards for data table")
    public LazyData<LazyChipCardDto> load(@RequestBody LazyCriteria criteria) {
        return this.serviceChipCard.load(criteria);
    }

    @GetMapping("/{id}")
    @Operation(description = "Loads chip card detail by id")
    public ChipCardDetail loadDetail(@PathVariable("id") Long id) {
        return this.serviceChipCard.loadDetail(id);
    }
}
