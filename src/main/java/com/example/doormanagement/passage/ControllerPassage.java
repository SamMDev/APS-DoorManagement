package com.example.doormanagement.passage;

import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.chipcard.dto.LazyChipCardPassage;
import com.example.doormanagement.gateway.dto.response.LazyGatewayPassageDto;
import com.example.doormanagement.passage.dto.RequestSavePassageDto;
import com.example.doormanagement.passage.dto.response.LazyDataPassageModel;
import com.example.doormanagement.passage.dto.response.PassageDetailDto;
import com.example.doormanagement.person.dto.response.LazyDataPassagePersonDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passage")
@RequiredArgsConstructor
public class ControllerPassage {

    private final ServicePassage servicePassage;

    @PutMapping("/save")
    @Operation(description = "Saves passage, returns if was successful or not")
    public boolean savePassage(@RequestBody RequestSavePassageDto passageDto) {
        return this.servicePassage.savePassage(passageDto);
    }

    @PostMapping("/load")
    @Operation(description = "Lazy loads passage data rows for data table")
    public LazyData<LazyDataPassageModel> loadLazyPassage(@RequestBody LazyCriteria criteria) {
        return this.servicePassage.loadLazy(criteria);
    }

    @GetMapping("/{id}")
    @Operation(description = "Gets passage detail by passage id")
    public PassageDetailDto getDetail(@PathVariable("id") Long id) {
        return this.servicePassage.loadDetail(id);
    }

    @PostMapping("/person/{id}")
    @Operation(description = "Lazy loads passages for given person")
    public LazyData<LazyDataPassagePersonDetailDto> loadPassagesForPerson(
            @PathVariable("id") Long personId,
            @RequestBody LazyCriteria criteria) {
        return this.servicePassage.loadPassagesForPerson(personId, criteria);
    }

    @PostMapping("/gateway/{id}")
    @Operation(description = "Lazy loads passages for gateway")
    public LazyData<LazyGatewayPassageDto> loadForGateway(
            @PathVariable("id") Long gatewayId,
            @RequestBody LazyCriteria criteria) {
        return this.servicePassage.loadForGateway(gatewayId, criteria);
    }

    @PostMapping("/chip-card/{id}")
    @Operation(description = "Lazy loads passages for chip card")
    public LazyData<LazyChipCardPassage> loadForChipCard(@PathVariable("id") Long chipCardId, @RequestBody LazyCriteria criteria) {
        return this.servicePassage.loadPassagesForChipCard(criteria, chipCardId);
    }


}
