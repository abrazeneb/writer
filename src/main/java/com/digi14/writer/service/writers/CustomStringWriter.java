package com.digi14.writer.service.writers;


import org.apache.commons.lang3.StringUtils;

public class CustomStringWriter extends Writer {

    public CustomStringWriter(final String content) {
        super(content);
    }

    @Override
    public String readContent() {
        return this.content;
    }

    @Override
    public CustomStringWriter write(String content) {
        if(!this.isClosed) {
            this.content = StringUtils.isBlank(this.content) ? content : this.content.concat(" " + content);
        }

        return this;
    }

}
