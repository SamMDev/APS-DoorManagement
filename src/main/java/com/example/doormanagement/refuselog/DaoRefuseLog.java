package com.example.doormanagement.refuselog;

import com.example.doormanagement.jdbi.BaseDao;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DaoRefuseLog extends BaseDao<EntityRefuseLog> {

    @Autowired
    public DaoRefuseLog(Jdbi jdbi) {
        super(EntityRefuseLog.class, jdbi);
    }


}
