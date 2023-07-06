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

import de.florianmichael.betapackets.base.ModelMapper;
import de.florianmichael.betapackets.base.bytebuf.FunctionalByteBuf;
import de.florianmichael.betapackets.base.packet.Packet;
import de.florianmichael.betapackets.model.base.ProtocolCollection;
import de.florianmichael.betapackets.model.game.PositionFlags;

import java.util.Objects;
import java.util.Set;

public class PlayerPositionAndLookS2CPacket extends Packet {
    public double x;
    public double y;
    public double z;

    public float yaw;
    public float pitch;

    public ModelMapper<Short, Set<PositionFlags>> positionFlags = new ModelMapper<>(FunctionalByteBuf::readUnsignedByte, FunctionalByteBuf::writeByte, PositionFlags::getFlags);

    public int teleportId1_9;

    public PlayerPositionAndLookS2CPacket(final FunctionalByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();

        this.yaw = buf.readFloat();
        this.pitch = buf.readFloat();

        this.positionFlags.read(buf);
        if (buf.getProtocolVersion().isNewerThanOrEqualTo(ProtocolCollection.R1_9)) {
            this.teleportId1_9 = buf.readVarInt();
        }
    }

    public PlayerPositionAndLookS2CPacket(double x, double y, double z, float yaw, float pitch, Set<PositionFlags> positionFlags) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;

        this.positionFlags = new ModelMapper<>(FunctionalByteBuf::writeByte, positionFlags);
    }

    public PlayerPositionAndLookS2CPacket(double x, double y, double z, float yaw, float pitch, Set<PositionFlags> positionFlags, int teleportId) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;

        this.positionFlags = new ModelMapper<>(FunctionalByteBuf::writeByte, positionFlags);
        this.teleportId1_9 = teleportId;
    }

    @Override
    public void write(FunctionalByteBuf buf) throws Exception {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);

        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);

        this.positionFlags.write(buf);
        if (buf.getProtocolVersion().isNewerThanOrEqualTo(ProtocolCollection.R1_9)) {
            buf.writeVarInt(this.teleportId1_9);
        }
    }

    @Override
    public String toString() {
        return "PlayerPositionAndLookS2CPacket{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                ", positionFlags=" + positionFlags +
                ", teleportId1_9=" + teleportId1_9 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerPositionAndLookS2CPacket that = (PlayerPositionAndLookS2CPacket) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (Double.compare(that.z, z) != 0) return false;
        if (Float.compare(that.yaw, yaw) != 0) return false;
        if (Float.compare(that.pitch, pitch) != 0) return false;
        if (teleportId1_9 != that.teleportId1_9) return false;
        return Objects.equals(positionFlags, that.positionFlags);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (yaw != +0.0f ? Float.floatToIntBits(yaw) : 0);
        result = 31 * result + (pitch != +0.0f ? Float.floatToIntBits(pitch) : 0);
        result = 31 * result + (positionFlags != null ? positionFlags.hashCode() : 0);
        result = 31 * result + teleportId1_9;
        return result;
    }
}