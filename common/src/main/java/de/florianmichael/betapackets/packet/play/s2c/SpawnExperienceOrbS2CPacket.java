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

import de.florianmichael.betapackets.base.PacketTransformer;
import de.florianmichael.betapackets.base.packet.Packet;

public class SpawnExperienceOrbS2CPacket extends Packet {

    public int entityId;

    public int x;
    public int y;
    public int z;

    public int count;

    public SpawnExperienceOrbS2CPacket(final PacketTransformer transformer) {
        this(transformer.readVarInt(), transformer.readInt(), transformer.readInt(), transformer.readInt(), transformer.readShort());
    }

    public SpawnExperienceOrbS2CPacket(int entityId, int x, int y, int z, int count) {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
    }

    @Override
    public void write(PacketTransformer buf) throws Exception {
        buf.writeVarInt(entityId);

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        buf.writeShort(count);
    }

    @Override
    public String toString() {
        return "SpawnExperienceOrbS2CPacket{" +
                "entityId=" + entityId +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", count=" + count +
                '}';
    }
}
