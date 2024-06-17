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

package dev.httpmarco.polocloud.bungeecord.listener;

import dev.httpmarco.polocloud.api.CloudAPI;
import dev.httpmarco.polocloud.api.packets.player.CloudPlayerServiceChangePacket;
import dev.httpmarco.polocloud.runner.CloudInstance;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public final class ServerConnectedListener implements Listener {

    @EventHandler
    public void handleServerConnect(ServerConnectedEvent event) {
        var player = event.getPlayer();
        var cloudService = CloudAPI.instance().serviceProvider().service(event.getServer().getInfo().getName());
        CloudInstance.instance().client().transmitter().sendPacket(new CloudPlayerServiceChangePacket(player.getUniqueId(), cloudService.id()));
    }
}