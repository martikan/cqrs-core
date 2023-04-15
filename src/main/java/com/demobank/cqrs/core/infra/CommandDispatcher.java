package com.demobank.cqrs.core.infra;

import com.demobank.cqrs.core.command.BaseCommand;
import com.demobank.cqrs.core.command.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand command);
}
