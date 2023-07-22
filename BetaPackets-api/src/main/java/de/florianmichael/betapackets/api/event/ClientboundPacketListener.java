/*
 * This file is part of BetaPackets - https://github.com/FlorianMichael/BetaPackets
 * Copyright (C) 2023 FlorianMichael/EnZaXD and contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.florianmichael.betapackets.api.event;

import de.florianmichael.betapackets.base.UserConnection;
import de.florianmichael.betapackets.base.packet.Packet;
import de.florianmichael.betapackets.model.base.NetworkState;
import de.florianmichael.dietrichevents2.CancellableEvent;

/**
 * This interface is used to listen to clientbound packets.
 */
public interface ClientboundPacketListener {

    /**
     * This method is called when a clientbound packet is sent.
     * @param event The event.
     */
    void onClientBoundPacket(ClientboundPacketEvent<?> event);

    /**
     * This class represents the event that is called when a clientbound packet is sent.
     */
    class ClientboundPacketEvent<T> extends CancellableEvent<ClientboundPacketListener> {
        public final static int ID = 0;

        public UserConnection userConnection;
        public NetworkState networkState;
        public Packet packet;
        public T player;

        public ClientboundPacketEvent(UserConnection userConnection, NetworkState networkState, Packet packet, T player) {
            this.userConnection = userConnection;
            this.networkState = networkState;
            this.packet = packet;
            this.player = player;
        }


        @Override
        public void call(ClientboundPacketListener listener) {
            listener.onClientBoundPacket(this);
        }
    }
}
