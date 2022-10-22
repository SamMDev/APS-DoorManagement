package com.example.doormanagement.passage;

import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.chipcard.DaoChipCard;
import com.example.doormanagement.chipcard.dto.LazyChipCardPassage;
import com.example.doormanagement.gateway.DaoGateway;
import com.example.doormanagement.gateway.EntityGateway;
import com.example.doormanagement.gateway.dto.response.LazyGatewayPassageDto;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.passage.dto.RequestSavePassageDto;
import com.example.doormanagement.passage.dto.response.LazyDataPassageModel;
import com.example.doormanagement.passage.dto.response.PassageDetailDto;
import com.example.doormanagement.person.dto.response.LazyDataPassagePersonDetailDto;
import com.example.doormanagement.refuselog.ServiceRefuseLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Samuel Molcan
 */
@Service
@RequiredArgsConstructor
public class ServicePassage {

    private final DaoPassage daoPassage;
    private final DaoChipCard daoChipCard;
    private final DaoGateway daoGateway;
    private final ServiceRefuseLog serviceRefuseLog;


    /**
     * Saves passage
     *
     * @param passageDto    passage info
     * @return              if save was successful or not
     */
    public boolean savePassage(RequestSavePassageDto passageDto) {
        // gateway with given gatewayCode should always exist
        final EntityGateway gateway = this.daoGateway.findByCode(passageDto.getGatewayCode()).orElseThrow();

        // find chip card given serial number belongs to
        return this.daoChipCard.findForSerialNumber(passageDto.getChipCardSerialNumber())
                .map(chipCard -> {
                    this.daoPassage.insert(new EntityPassage(gateway.getId(), chipCard.getId(), LocalDateTime.now()));
                    return true;
                })
                .orElseGet(() -> {
                    this.serviceRefuseLog.logChipCardNotFound(passageDto.getChipCardSerialNumber(), gateway.getId());
                    return false;
                });
    }


    /**
     * Lazy loads passages for datatable
     *
     * @param criteria  load criteria
     * @return          loaded
     */
    public LazyData<LazyDataPassageModel> loadLazy(LazyCriteria criteria) {
        final List<JoinedEntity> loadedLazy = this.daoPassage.loadLazyPassageForTable(criteria);

        return new LazyData<>(
                loadedLazy.stream().map(LazyDataPassageModel::new).toList(),
                this.daoPassage.countAll(),
                criteria.getOffset(),
                criteria.getLimit()
        );
    }

    /**
     * Loads passage detail
     *
     * @param id    passage id
     * @return      as dto
     */
    public PassageDetailDto loadDetail(Long id) {
        return this.daoPassage.loadPassageDetail(id)
                .map(PassageDetailDto::new)
                .orElse(null);
    }

    /**
     * Lazy loads passages for person
     *
     * @param personId  for person
     * @param criteria  load criteria
     * @return          loaded dto
     */
    public LazyData<LazyDataPassagePersonDetailDto> loadPassagesForPerson(Long personId, LazyCriteria criteria) {
        final List<LazyDataPassagePersonDetailDto> data = this.daoPassage.loadPassagesForPersonDetail(criteria, personId)
                .stream()
                .map(LazyDataPassagePersonDetailDto::new)
                .toList();

        return new LazyData<>(data, this.daoPassage.countPassagesForPerson(personId), criteria.getOffset(), criteria.getLimit());
    }

    /**
     * Lazy loads passages for gateway
     *
     * @param gatewayId     for gateway
     * @param criteria      load criteria
     * @return              loaded
     */
    public LazyData<LazyGatewayPassageDto> loadForGateway(Long gatewayId, LazyCriteria criteria) {
        return new LazyData<>(
                this.daoPassage.loadForGateway(gatewayId, criteria).stream().map(LazyGatewayPassageDto::new).toList(),
                this.daoPassage.countForGateway(gatewayId),
                criteria.getOffset(),
                criteria.getLimit()
        );
    }

    /**
     * Lazy loads passages for chip card
     *
     * @param criteria      load criteria
     * @param chipCardId    for chip card
     * @return              loaded
     */
    public LazyData<LazyChipCardPassage> loadPassagesForChipCard(LazyCriteria criteria, Long chipCardId) {
        return new LazyData<>(
                this.daoPassage.loadForChipCard(criteria, chipCardId).stream().map(LazyChipCardPassage::new).toList(),
                this.daoPassage.countForChipCard(chipCardId),
                criteria.getOffset(),
                criteria.getLimit()
        );
    }
}
