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

package de.florianmichael.betapackets.packet.model.play.c2s;

import de.florianmichael.betapackets.base.bytebuf.FunctionalByteBuf;
import de.florianmichael.betapackets.event.PacketEvent;
import de.florianmichael.betapackets.packet.model.PacketWrapper;
import de.florianmichael.betapackets.packet.type.Packet;

import java.io.IOException;

public class WrapperPlayClientKeepAlive extends PacketWrapper<WrapperPlayClientKeepAlive> {

    private long id;

    public WrapperPlayClientKeepAlive(PacketEvent event) throws IOException {
        super(event);
    }

    public WrapperPlayClientKeepAlive(long id) {
        this.id = id;
    }

    @Override
    public void write(Packet type, FunctionalByteBuf buf) throws IOException {
        buf.writeLong(id);
    }

    @Override
    public void read(Packet type, FunctionalByteBuf buf) throws IOException {
        id = buf.readLong();
    }

    @Override
    public void copyFrom(WrapperPlayClientKeepAlive base) {
        id = base.id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}