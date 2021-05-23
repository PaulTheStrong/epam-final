package com.epam.web.command;


/**
 * CommandResult class provides convenient way to define
 * redirect or forward actions into commands.
 */
public class CommandResult {

    private final String page;
    private final boolean isRedirect;

    private CommandResult(String page, boolean redirect) {
        this.page = page;
        this.isRedirect = redirect;
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
