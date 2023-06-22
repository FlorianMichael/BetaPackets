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

package de.florianmichael.betapackets.packet.play.s2c;

import de.florianmichael.betapackets.base.Packet;
import de.florianmichael.betapackets.base.bytebuf.FunctionalByteBuf;

public class WindowPropertyS2CPacket extends Packet {

    public int windowId;
    public int varIndex;
    public int varValue;

    public WindowPropertyS2CPacket(final FunctionalByteBuf buf) {
        this(buf.readUnsignedByte(), buf.readShort(), buf.readShort());
    }

    public WindowPropertyS2CPacket(int windowId, int varIndex, int varValue) {
        this.windowId = windowId;
        this.varIndex = varIndex;
        this.varValue = varValue;
    }

    @Override
    public void write(FunctionalByteBuf buf) throws Exception {
        buf.writeByte(windowId);
        buf.writeShort(varIndex);
        buf.writeShort(varValue);
    }

    @Override
    public String toString() {
        return "WindowPropertyS2CPacket{" +
                "windowId=" + windowId +
                ", varIndex=" + varIndex +
                ", varValue=" + varValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WindowPropertyS2CPacket that = (WindowPropertyS2CPacket) o;

        if (windowId != that.windowId) return false;
        if (varIndex != that.varIndex) return false;
        return varValue == that.varValue;
    }

    @Override
    public int hashCode() {
        int result = windowId;
        result = 31 * result + varIndex;
        result = 31 * result + varValue;
        return result;
    }
}