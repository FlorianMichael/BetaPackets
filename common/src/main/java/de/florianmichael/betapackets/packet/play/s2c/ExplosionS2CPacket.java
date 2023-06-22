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
import de.florianmichael.betapackets.base.Packet;
import de.florianmichael.betapackets.model.position.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExplosionS2CPacket extends Packet {

    public double x;
    public double y;
    public double z;

    public float strength;
    public List<BlockPos> affectedBlockPositions;

    public float motionX;
    public float motionY;
    public float motionZ;

    public ExplosionS2CPacket(final FunctionalByteBuf transformer) {
        this.x = transformer.readFloat();
        this.y = transformer.readFloat();
        this.z = transformer.readFloat();

        this.strength = transformer.readFloat();
        this.affectedBlockPositions = new ArrayList<>();

        final int size = transformer.readInt();
        for (int i = 0; i < size; ++i) {
            final int x = transformer.readByte() + (int) this.x;
            final int y = transformer.readByte() + (int) this.y;
            final int z = transformer.readByte() + (int) this.z;

            this.affectedBlockPositions.add(new BlockPos(x, y, z));
        }

        this.motionX = transformer.readFloat();
        this.motionY = transformer.readFloat();
        this.motionZ = transformer.readFloat();
    }

    public ExplosionS2CPacket(double x, double y, double z, float strength, List<BlockPos> affectedBlockPositions) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.strength = strength;
        this.affectedBlockPositions = affectedBlockPositions;
    }

    public ExplosionS2CPacket(double x, double y, double z, float strength, List<BlockPos> affectedBlockPositions, float motionX, float motionY, float motionZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.strength = strength;
        this.affectedBlockPositions = affectedBlockPositions;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }

    @Override
    public void write(FunctionalByteBuf buf) throws Exception {
        buf.writeFloat((float) this.x);
        buf.writeFloat((float) this.y);
        buf.writeFloat((float) this.z);

        buf.writeFloat(this.strength);
        buf.writeInt(this.affectedBlockPositions.size());

        for (final BlockPos blockPos : this.affectedBlockPositions) {
            final int x = blockPos.x - (int) this.x;
            final int y = blockPos.y - (int) this.y;
            final int z = blockPos.z - (int) this.z;

            buf.writeByte(x);
            buf.writeByte(y);
            buf.writeByte(z);
        }

        buf.writeFloat(this.motionX);
        buf.writeFloat(this.motionY);
        buf.writeFloat(this.motionZ);
    }

    @Override
    public String toString() {
        return "ExplosionS2CPacket{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", strength=" + strength +
                ", affectedBlockPositions=" + affectedBlockPositions +
                ", motionX=" + motionX +
                ", motionY=" + motionY +
                ", motionZ=" + motionZ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExplosionS2CPacket that = (ExplosionS2CPacket) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (Double.compare(that.z, z) != 0) return false;
        if (Float.compare(that.strength, strength) != 0) return false;
        if (Float.compare(that.motionX, motionX) != 0) return false;
        if (Float.compare(that.motionY, motionY) != 0) return false;
        if (Float.compare(that.motionZ, motionZ) != 0) return false;
        return Objects.equals(affectedBlockPositions, that.affectedBlockPositions);
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
        result = 31 * result + (strength != +0.0f ? Float.floatToIntBits(strength) : 0);
        result = 31 * result + (affectedBlockPositions != null ? affectedBlockPositions.hashCode() : 0);
        result = 31 * result + (motionX != +0.0f ? Float.floatToIntBits(motionX) : 0);
        result = 31 * result + (motionY != +0.0f ? Float.floatToIntBits(motionY) : 0);
        result = 31 * result + (motionZ != +0.0f ? Float.floatToIntBits(motionZ) : 0);
        return result;
    }
}