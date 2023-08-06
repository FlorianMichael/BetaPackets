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

package de.florianmichael.betapackets.api.netty.element;

import de.florianmichael.betapackets.BetaPackets;
import de.florianmichael.betapackets.base.UserConnection;
import de.florianmichael.betapackets.base.bytebuf.FunctionalByteBuf;
import de.florianmichael.betapackets.event.PacketEvent;
import de.florianmichael.betapackets.packet.type.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * This decoder decodes all incoming packets and executes the BetaPacketsAPI in the process
 */
public class BetaPacketsDecoder extends MessageToMessageDecoder<ByteBuf> {

    /**
     * The user connection of the player
     */
    private final UserConnection userConnection;

    public BetaPacketsDecoder(UserConnection userConnection) {
        this.userConnection = userConnection;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println("PIPELINE: " + ctx.pipeline().names());
        FunctionalByteBuf readBuffer = new FunctionalByteBuf(msg, userConnection);
        int packetId = readBuffer.readVarInt();

        List<Packet> packets = userConnection.getC2SPackets();
        if (packetId >= packets.size())
            throw new DecoderException("Unknown packet-id " + packetId);

        PacketEvent event = new PacketEvent(packets.get(packetId), readBuffer, userConnection);
        BetaPackets.getAPI().fireReadEvent(event);

        packetId = packets.indexOf(event.getType());
        if (packetId == -1)
            throw new DecoderException("Unregistered packet-type " + event.getType());

        ByteBuf outBuf = ctx.alloc().buffer();
        FunctionalByteBuf writeBuffer = new FunctionalByteBuf(outBuf, userConnection);
        writeBuffer.writeVarInt(packetId);

        if (event.getLastPacketWrapper() != null) { // packet was edited
            event.getLastPacketWrapper().write(event.getType(), writeBuffer);
        } else { // pass-through
            writeBuffer.writeBytes(msg);
        }
        out.add(outBuf);
    }

    public UserConnection getUserConnection() {
        return userConnection;
    }

    @Override
    public boolean isSharable() {
        return this.userConnection != null;
    }
}
