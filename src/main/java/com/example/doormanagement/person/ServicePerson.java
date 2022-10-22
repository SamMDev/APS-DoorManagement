package com.example.doormanagement.person;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.person.dto.response.LazyDataPerson;
import com.example.doormanagement.person.dto.response.PersonDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicePerson {

    private final DaoPerson daoPerson;


    public PersonDetailDto getDetail(Long id) {
        return this.daoPerson.getDataForPersonDetail(id)
                .map(PersonDetailDto::new)
                .orElse(null);
    }

    public LazyData<LazyDataPerson> load(LazyCriteria criteria) {
        return new LazyData<>(
                this.daoPerson.loadByCriteria(criteria).stream().map(LazyDataPerson::new).toList(),
                this.daoPerson.countAll(),
                criteria.getOffset(),
                criteria.getLimit()
        );
    }
}
