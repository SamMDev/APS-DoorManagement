package com.example.doormanagement.gateway;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.gateway.dto.response.GatewayDetail;
import com.example.doormanagement.gateway.dto.response.LazyGatewayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Samuel Molcan
 */
@Service
@RequiredArgsConstructor
public class ServiceGateway {

    private final DaoGateway daoGateway;

    /**
     * Lazy loads gateways for datatable
     *
     * @param criteria  load criteria
     * @return          loaded
     */
    public LazyData<LazyGatewayDto> load(LazyCriteria criteria) {
        return new LazyData<>(
                this.daoGateway.load(criteria).stream().map(LazyGatewayDto::new).toList(),
                this.daoGateway.countAll(),
                criteria.getOffset(),
                criteria.getLimit()
        );
    }

    /**
     * Gets gateway detail dto by id
     *
     * @param id    gateway id
     * @return      detail dto
     */
    public GatewayDetail findDetail(Long id) {
        return this.daoGateway.findById(id)
                .map(GatewayDetail::new)
                .orElse(null);
    }

}
