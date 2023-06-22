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

import de.florianmichael.betapackets.base.bytebuf.FunctionalByteBuf;

public class EntityLookS2CPacket extends EntityS2CPacket {

    public byte yaw;
    public byte pitch;

    public boolean onGround;

    public EntityLookS2CPacket(FunctionalByteBuf buf) {
        super(buf);

        this.yaw = buf.readByte();
        this.pitch = buf.readByte();

        this.onGround = buf.readBoolean();
    }

    public EntityLookS2CPacket(int entityId, byte yaw, byte pitch, boolean onGround) {
        super(entityId);

        this.yaw = yaw;
        this.pitch = pitch;

        this.onGround = onGround;
    }

    @Override
    public void write(FunctionalByteBuf buf) throws Exception {
        super.write(buf);

        buf.writeByte(yaw);
        buf.writeByte(pitch);

        buf.writeBoolean(onGround);
    }

    @Override
    public String toString() {
        return "EntityLookS2CPacket{" +
                "yaw=" + yaw +
                ", pitch=" + pitch +
                ", onGround=" + onGround +
                ", entityId=" + entityId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityLookS2CPacket that = (EntityLookS2CPacket) o;

        if (yaw != that.yaw) return false;
        if (pitch != that.pitch) return false;
        return onGround == that.onGround;
    }

    @Override
    public int hashCode() {
        int result = yaw;
        result = 31 * result + (int) pitch;
        result = 31 * result + (onGround ? 1 : 0);
        return result;
    }
}