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

package de.florianmichael.betapackets.model.world.chunk.palette;

import de.florianmichael.betapackets.netty.bytebuf.FunctionalByteBuf;

public class SingularPalette implements Palette {

    private int id;

    public SingularPalette(FunctionalByteBuf buf) {
        id = buf.readVarInt();
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public int getStorageId(int registryId) {
        if (registryId == id) return 0;
        else return -1;
    }

    @Override
    public int getRegistryId(int storageId) {
        if (storageId == 0) return id;
        else return 0;
    }
}
