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

package de.florianmichael.betapackets.packet.login.s2c;

import de.florianmichael.betapackets.base.bytebuf.FunctionalByteBuf;
import de.florianmichael.betapackets.base.packet.Packet;
import net.lenni0451.mcstructs.text.ATextComponent;

import java.util.Objects;

public class LoginDisconnectS2CPacket extends Packet {
    public ATextComponent reason;

    public LoginDisconnectS2CPacket(final FunctionalByteBuf buf) {
        this(buf.readComponent());
    }

    public LoginDisconnectS2CPacket(ATextComponent reason) {
        this.reason = reason;
    }

    @Override
    public void write(FunctionalByteBuf buf) {
        buf.writeComponent(reason);
    }

    @Override
    public String toString() {
        return "LoginDisconnectS2CPacket{" +
                "reason='" + reason + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginDisconnectS2CPacket that = (LoginDisconnectS2CPacket) o;

        return Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return reason != null ? reason.hashCode() : 0;
    }
}