package com.digi14.writer.service.writers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public abstract class Writer {

    @Getter
    protected String content;
    @Getter
    protected boolean isClosed;

    public Writer() {
    }

    public Writer(String content) {
        this.content = content;
    }

    public Writer toLowerCase() {
        if (StringUtils.isNotBlank(this.content)) {
        log.trace("Converting content: {} to lower case", this.content);
            this.content = this.content.toLowerCase();
        }
        return this;
    }

    public Writer toUpperCase() {
        if (StringUtils.isNotBlank(this.content)) {
            log.trace("Converting content: {} to upper case", this.content);
            this.content = this.content.toUpperCase();
        }
        return this;
    }

    public Writer removeStupid() {
        if (StringUtils.isNotBlank(this.content)) {
            log.trace("Sanitizing content: {} to upper case", this.content);
            this.content = this.content.replace("stupid", "s*****");
        }
        return this;
    }

    public Writer removeDuplicateWords() {
        if (StringUtils.isNotBlank(this.content)) {
            this.content = this.content.replaceAll("(?i)\\b([a-z]+)\\b(?:\\s+\\1\\b)+", "$1");
        }
        return this;
    }

    public void close() {
        this.isClosed = true;
    }

    public abstract String readContent();
    public abstract Writer write(final String content);
}
