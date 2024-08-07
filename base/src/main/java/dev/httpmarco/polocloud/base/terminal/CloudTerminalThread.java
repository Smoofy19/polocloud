/*
 * Copyright 2024 Mirco Lindenau | HttpMarco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.httpmarco.polocloud.base.terminal;

import dev.httpmarco.polocloud.api.CloudAPI;
import dev.httpmarco.polocloud.api.CloudProperty;
import dev.httpmarco.polocloud.base.NodeShutdownTask;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;

public final class CloudTerminalThread extends Thread {
    private final String prompt;
    private final CloudTerminal terminal;

    public CloudTerminalThread(CloudTerminal terminal) {
        this.terminal = terminal;

        setName("console-reading-thread");

        var globalProperties = CloudAPI.instance().globalProperties();
        this.prompt = this.terminal.includeColorCodes(globalProperties.has(CloudProperty.PROMPT) ? globalProperties.property(CloudProperty.PROMPT) : "&7cloud &8» &7");

        setContextClassLoader(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                try {
                    try {
                        final var rawLine = terminal.lineReader().readLine(prompt);
                        final var line = rawLine.split(" ");

                        if (line.length > 0) {
                            terminal.commandService().call(line);
                        }
                    } catch (EndOfFileException ignore) {}
                } catch (UserInterruptException exception) {
                    NodeShutdownTask.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
