package com.demobank.cqrs.core.infra;

import com.demobank.cqrs.core.domain.BaseEntity;
import com.demobank.cqrs.core.query.BaseQuery;
import com.demobank.cqrs.core.query.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
