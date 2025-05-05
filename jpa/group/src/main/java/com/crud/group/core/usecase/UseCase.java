package com.crud.group.core.usecase;

import jakarta.transaction.Transactional;

public interface UseCase<Req extends Request,Res extends Response> {

    @Transactional
    Res handle(Req req);

}
