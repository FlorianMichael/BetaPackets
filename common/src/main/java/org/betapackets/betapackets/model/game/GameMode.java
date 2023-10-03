/*
 * This file is part of BetaPackets - https://github.com/BetaPackets/BetaPackets
 * Copyright (C) 2023 FlorianMichael/EnZaXD
 * Copyright (C) 2023 DietrichPaul and contributors
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

package org.betapackets.betapackets.model.game;

import org.betapackets.betapackets.model.base.ProtocolCollection;

public enum GameMode {

    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR;

    public int getId() {
        return ordinal();
    }

    public static GameMode getOrNull(final ProtocolCollection version, final short id) {
        if (id == -1) return null;
        for (GameMode value : values()) {
            if (value.getId() == id) return value;
        }
        return null;
    }

    public static GameMode getById(final ProtocolCollection version, final short id) {
        for (GameMode value : values()) {
            if (value.getId() == id) return value;
        }
        return null;
    }
}