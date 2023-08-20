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

/*
This is located (yarn mappings) PalettedContainer.PaletteProvider
 */
public enum PaletteProvider {

    CHUNK(4, 8, 4096),
    BIOME(1, 3, 64);

    private int minBits;
    private int maxBits;
    private int storage;

    PaletteProvider(int minBits, int maxBits, int storage) {
        this.minBits = minBits;
        this.maxBits = maxBits;
        this.storage = storage;
    }

    public Palette readPalette(FunctionalByteBuf buf, int bits) {
        if (bits > maxBits) {
            return new PassThroughPalette();
        } else if (bits == 0) {
            return new SingularPalette(buf);
        } else if (bits <= minBits) {
            return new ArrayPalette(buf, bits);
        } else {
            return new BiMapPalette(buf, bits);
        }
    }

    public int getMaxBits() {
        return maxBits;
    }

    public int getStorage() {
        return storage;
    }

    public int getMinBits() {
        return minBits;
    }
}
