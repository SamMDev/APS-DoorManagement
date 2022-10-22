package com.example.doormanagement.chipcard;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.chipcard.dto.ChipCardDetail;
import com.example.doormanagement.chipcard.dto.LazyChipCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Samuel Molcan
 */
@Service
@RequiredArgsConstructor
public class ServiceChipCard {

    private final DaoChipCard daoChipCard;

    public LazyData<LazyChipCardDto> load(LazyCriteria criteria) {
        return new LazyData<>(
                this.daoChipCard.load(criteria).stream().map(LazyChipCardDto::new).toList(),
                this.daoChipCard.countAll(),
                criteria.getOffset(),
                criteria.getLimit()
        );
    }

    public ChipCardDetail loadDetail(Long id) {
        return this.daoChipCard.findChipCardDetail(id)
                .map(ChipCardDetail::new)
                .orElse(null);
    }


}
