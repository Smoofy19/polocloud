package dev.httpmarco.polocloud.node.terminal.commands;

import dev.httpmarco.polocloud.node.Node;
import dev.httpmarco.polocloud.node.commands.Command;
import dev.httpmarco.polocloud.api.properties.PropertyRegister;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class InfoCommand extends Command {

    public InfoCommand() {
        super("info", "Displays some information", "me");

        defaultExecution(context -> {
            log.info("Java version&8: &f{}", System.getProperty("java.version"));
            log.info("Operating System&8: &f{}", System.getProperty("os.name"));
            log.info("Used Memory of the Node process&8: &f{}", this.usedMemory());
            log.info(" ");
            log.info("Registered Groups&8: &f{}", Node.instance().groupProvider().groups().size());
            log.info("Online Services&8: &f{}", Node.instance().serviceProvider().services().size());
            log.info("Registered Platforms: &f{} &8(&f{} versions&8)", Node.instance().platformService().platforms().size(), Node.instance().platformService().versionsAmount());
            log.info("Node endpoints&8: &f{}", Node.instance().clusterProvider().endpoints().size());
            log.info(" ");
            log.info("Registered properties&8: &f{}", PropertyRegister.propertiesAmount());
        });
    }

    private String usedMemory() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + " mb";
    }

}
