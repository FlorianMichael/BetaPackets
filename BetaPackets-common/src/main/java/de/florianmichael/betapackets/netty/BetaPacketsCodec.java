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

package de.florianmichael.betapackets.netty;

import de.florianmichael.betapackets.connection.UserConnection;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * This codec is a merge of the {@link BetaPacketsDecoder} and {@link BetaPacketsEncoder} and it can be used in codec pipelines.
 */
public class BetaPacketsCodec extends MessageToMessageCodec<ByteBuf, ByteBuf> {
    private final BetaPacketsDecoder decoder;
    private final BetaPacketsEncoder encoder;

    private final UserConnection userConnection;

    /**
     * Creates a new codec for the given user connection
     * @param userConnection The user connection
     */
    public BetaPacketsCodec(UserConnection userConnection) {
        this.decoder = new BetaPacketsDecoder(userConnection);
        this.encoder = new BetaPacketsEncoder(userConnection);

        this.userConnection = userConnection;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        encoder.encode(ctx, msg, out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        decoder.decode(ctx, msg, out);
    }

    public UserConnection getUserConnection() {
        return userConnection;
    }

    @Override
    public boolean isSharable() {
        return this.userConnection != null;
    }
}